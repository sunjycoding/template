package com.example.template.modules.codegen.service.impl;

import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.codegen.dto.CodegenTableColumnInfoDTO;
import com.example.template.modules.codegen.dto.CodegenTableInfoCriteriaDTO;
import com.example.template.modules.codegen.dto.CodegenTableInfoDTO;
import com.example.template.modules.codegen.repository.CodegenRepository;
import com.example.template.modules.codegen.service.CodegenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author created by sunjy on 12/28/23
 */
@RequiredArgsConstructor
@Service
public class CodegenServiceImpl implements CodegenService {

    private final CodegenRepository codegenRepository;

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
