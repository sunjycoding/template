package com.example.template;

import com.example.template.common.service.FreemarkerTemplateService;
import com.example.template.modules.codegen.service.CodegenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TemplateApplicationTests {

    @Autowired
    private FreemarkerTemplateService freemarkerTemplateService;

    @Autowired
    private CodegenService codegenService;

    @Test
    void contextLoads() {
    }

    final String controllerPath = "codegen/backend/Controller.ftl";

    @Test
    void test() {
//        Map<String, Object> model = new HashMap<>();
//        model.put("author", "sunjy");
//        model.put("date", "12/28/23");
//        model.put("namePlural", "users");
//        model.put("moduleLowerCase", "system");
//        model.put("moduleUpperCase", "System");
//        model.put("nameLowerCase", "user");
//        model.put("nameUpperCase", "User");
//        String content = freemarkerTemplateService.renderTemplate(controllerPath, model);
//        try (FileWriter writer = new FileWriter("outputFile.java")) {
//            writer.write(content);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }

//        List<CodegenTableInfoDTO> codegenTableInfoDTOList = codegenService.listTableInfo();
//        System.out.println(codegenTableInfoDTOList);
//        codegenTableInfoDTOList.forEach(codegenTableInfoDTO -> {
//            String tableName = codegenTableInfoDTO.getName();
//            System.out.println("===" + codegenTableInfoDTO.getName() + "===");
//            System.out.println(codegenService.getColumnInfo(tableName));
//        });
    }
}
