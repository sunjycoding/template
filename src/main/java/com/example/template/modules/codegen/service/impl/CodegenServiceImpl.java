package com.example.template.modules.codegen.service.impl;

import com.example.template.common.constants.AppConstants;
import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.common.service.FreemarkerTemplateService;
import com.example.template.common.util.DateUtils;
import com.example.template.modules.codegen.dto.CodegenTableColumnInfoDTO;
import com.example.template.modules.codegen.dto.CodegenTableInfoCriteriaDTO;
import com.example.template.modules.codegen.dto.CodegenTableInfoDTO;
import com.example.template.modules.codegen.repository.CodegenRepository;
import com.example.template.modules.codegen.service.CodegenService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author created by sunjy on 12/28/23
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CodegenServiceImpl implements CodegenService {

    private static final Map<String, String> MYSQL_TO_JAVA_TYPE_MAP = new HashMap<>();
    private static final String BACKEND_PATH = "codegen/backend/";
    private static final String FRONTEND_PATH = "codegen/frontend/";

    static {
        MYSQL_TO_JAVA_TYPE_MAP.put("varchar", "String");
        MYSQL_TO_JAVA_TYPE_MAP.put("int", "Integer");
        MYSQL_TO_JAVA_TYPE_MAP.put("boolean", "Boolean");
        MYSQL_TO_JAVA_TYPE_MAP.put("tinyint", "Boolean");
        MYSQL_TO_JAVA_TYPE_MAP.put("bool", "Boolean");
        MYSQL_TO_JAVA_TYPE_MAP.put("datetime", "LocalDateTime");
    }

    private final CodegenRepository codegenRepository;

    private final FreemarkerTemplateService freemarkerTemplateService;

    @Override
    public Pagination<CodegenTableInfoDTO> page(PaginationRequest paginationRequest,
                                                CodegenTableInfoCriteriaDTO codegenTableInfoCriteriaDTO) {
        int pageNumber = paginationRequest.getPageNumber();
        int pageSize = paginationRequest.getPageSize();
        List<CodegenTableInfoDTO> codegenTableInfoDTOList = new ArrayList<>();
        String tableName = codegenTableInfoCriteriaDTO.getName();
        List<Object[]> tableObjectList;
        if (StringUtils.hasText(tableName)) {
            tableObjectList = codegenRepository.findAllTable(pageNumber, pageSize, tableName);
        } else {
            tableObjectList = codegenRepository.findAllTable(pageNumber, pageSize);
        }
        for (Object[] objects : tableObjectList) {
            CodegenTableInfoDTO codegenTableInfoDTO = new CodegenTableInfoDTO();
            codegenTableInfoDTO.setName((String) objects[0]);
            codegenTableInfoDTO.setComment((String) objects[1]);
            codegenTableInfoDTOList.add(codegenTableInfoDTO);
        }
        Long totalElements = codegenRepository.getTableTotalElements();
        Pagination<CodegenTableInfoDTO> pagination = new Pagination<>();
        pagination.setPageNumber(pageNumber);
        pagination.setPageSize(pageSize);
        pagination.setTotalElements(totalElements);
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);
        pagination.setTotalPages(totalPages);
        pagination.setContent(codegenTableInfoDTOList);
        return pagination;
    }

    @Override
    public List<CodegenTableInfoDTO> listTableInfo() {
        List<CodegenTableInfoDTO> codegenTableInfoDTOList = new ArrayList<>();
        List<Object[]> tableObjectList = codegenRepository.findAllTable();
        for (Object[] objects : tableObjectList) {
            CodegenTableInfoDTO codegenTableInfoDTO = new CodegenTableInfoDTO();
            codegenTableInfoDTO.setName((String) objects[0]);
            codegenTableInfoDTO.setComment((String) objects[1]);
            codegenTableInfoDTOList.add(codegenTableInfoDTO);
        }
        return codegenTableInfoDTOList;
    }

    @Override
    public List<CodegenTableColumnInfoDTO> listColumnInfo(String tableName) {
        List<CodegenTableColumnInfoDTO> codegenTableColumnInfoDTOList = new ArrayList<>();
        List<Object[]> columnObjectList = codegenRepository.findAllColumn(tableName);
        for (Object[] objects : columnObjectList) {
            CodegenTableColumnInfoDTO codegenTableColumnInfoDTO = new CodegenTableColumnInfoDTO();
            codegenTableColumnInfoDTO.setName((String) objects[0]);
            codegenTableColumnInfoDTO.setType((String) objects[1]);
            codegenTableColumnInfoDTO.setComment((String) objects[2]);
            codegenTableColumnInfoDTO.setNullable("YES".equals(objects[3]));
            codegenTableColumnInfoDTO.setUnique("UNI".equals(objects[4]) || "PRI".equals(objects[4]));
            codegenTableColumnInfoDTOList.add(codegenTableColumnInfoDTO);
        }
        return codegenTableColumnInfoDTOList;
    }

    @Override
    public void generate(HttpServletResponse response, String tableName) {
        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
            Map<String, Object> model = parseModel(tableName);
            String[] templateNames = {
                    "Controller.java.ftl",
                    "DTO.java.ftl",
                    "Model.java.ftl",
                    "Repository.java.ftl",
                    "Service.java.ftl",
                    "ServiceImpl.java.ftl",
                    "Api.js.ftl",
                    "Form.vue.ftl",
                    "List.vue.ftl"
            };

            for (String templateName : templateNames) {
                processTemplate(templateName, model, zos);
            }
        } catch (Exception e) {
            throw new RuntimeException("生成代码出现错误", e);
        }
    }

    private void processTemplate(String templateName, Map<String, Object> model, ZipOutputStream zos) throws IOException {
        String path = templateName.contains(".java") ? BACKEND_PATH : FRONTEND_PATH;
        String renderedContent = freemarkerTemplateService.renderTemplate(path + templateName, model);
        // 去掉.ftl
        String fileNameWithoutFtl = templateName.substring(0, templateName.length() - 4);
        String regex = "\\.";
        String[] fileNameSplit = fileNameWithoutFtl.split(regex);
        String templateMainName = fileNameSplit[0];
        String suffix = fileNameSplit[1];
        File tempFile = createTempFile(templateMainName, suffix, renderedContent);
        // 要加上模块名和表名
        String module = (String) model.get("moduleUpperCase");
        String name = (String) model.get("nameUpperCase");
        String fileName = module + name + fileNameWithoutFtl;
        String modelKeyWord = "Model";
        // Model文件名不要"Model"
        if (fileName.contains(modelKeyWord)) {
            fileName = fileName.replace(modelKeyWord, AppConstants.EMPTY_STRING);
        }
        addFileToZip(zos, tempFile, fileName);
        if (!tempFile.delete()) {
            log.error("删除临时文件{}失败", tempFile);
        }
    }

    private String getJavaType(String mysqlType) {
        return MYSQL_TO_JAVA_TYPE_MAP.getOrDefault(mysqlType, "Object");
    }

    private Map<String, Object> parseModel(String tableName) {
        Map<String, Object> model = new HashMap<>();
        // 获取表信息
        List<Object[]> tableObjectList = codegenRepository.getTable(tableName);
        Object[] tableObject = tableObjectList.getFirst();
        String tableComment = (String) tableObject[1];

        // 获取列信息
        List<CodegenTableColumnInfoDTO> codegenTableColumnInfoDTOList = listColumnInfo(tableName);
        // 排除掉BaseEntity里的字段, 并且转换成驼峰
        List<String> excludes = Arrays.asList("id", "created_by", "created_date", "last_modified_by", "last_modified_date");
        List<CodegenTableColumnInfoDTO> propertyList = codegenTableColumnInfoDTOList.stream()
                .filter(codegenTableColumnInfoDTO -> !excludes.contains(codegenTableColumnInfoDTO.getName()))
                .peek(property -> {
                    property.setName(toCamelCase(property.getName()));
                    property.setType(getJavaType(property.getType()));
                })
                .toList();

        String[] tableParts = tableName.split("_");
        String module = tableParts[1];
        String name = tableParts[2];

        String author = "sunjy";
        String date = DateUtils.currentDate(DateUtils.FORMATTER_MM_DD_YY);
        String moduleLowerCase = module.toLowerCase();
        String moduleUpperCase = capitalize(module);
        String nameLowerCase = name.toLowerCase();
        String nameUpperCase = capitalize(name);
        String namePlural;
        if (name.endsWith("y")) {
            namePlural = nameLowerCase.substring(0, nameLowerCase.length() - 1) + "ies";
        } else {
            namePlural = nameLowerCase + "s";
        }
        model.put("tableName", tableName);
        model.put("author", author);
        model.put("date", date);
        model.put("namePlural", namePlural);
        model.put("moduleLowerCase", moduleLowerCase);
        model.put("moduleUpperCase", moduleUpperCase);
        model.put("nameLowerCase", nameLowerCase);
        model.put("nameUpperCase", nameUpperCase);
        model.put("tableComment", tableComment);
        model.put("propertyList", propertyList);
        return model;
    }

    private File createTempFile(String fileName, String suffix, String content) throws IOException {
        File tempFile = File.createTempFile(fileName, suffix);
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }
        return tempFile;
    }

    private void addFileToZip(ZipOutputStream zos, File file, String fileName) {
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zos.putNextEntry(zipEntry);
            StreamUtils.copy(fis, zos);
            zos.closeEntry();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 将下划线分隔的字符串转换为驼峰命名法。
     * 例如 created_date 转换为 createdDate
     *
     * @param input 要转换的字符串
     * @return 转换后的驼峰命名字符串
     */
    private String toCamelCase(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean nextUpperCase = false;

        for (char ch : input.toCharArray()) {
            if (ch == '_') {
                nextUpperCase = true;
            } else if (nextUpperCase) {
                result.append(Character.toUpperCase(ch));
                nextUpperCase = false;
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

}
