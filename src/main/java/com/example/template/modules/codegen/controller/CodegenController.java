package com.example.template.modules.codegen.controller;

import com.example.template.common.data.HttpResult;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.codegen.dto.CodegenTableInfoCriteriaDTO;
import com.example.template.modules.codegen.service.CodegenService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author created by sunjy on 12/21/23
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("system/codegen")
public class CodegenController {

    private final CodegenService codegenService;

    @GetMapping("page")
    public HttpResult page(PaginationRequest paginationRequest, CodegenTableInfoCriteriaDTO codegenTableInfoCriteriaDTO) {
        return HttpResult.success(codegenService.page(paginationRequest, codegenTableInfoCriteriaDTO));
    }

    @GetMapping
    public HttpResult list() {
        return HttpResult.success(codegenService.listTableInfo());
    }

    @GetMapping("column/{tableName}")
    public HttpResult listColumn(@PathVariable String tableName) {
        return HttpResult.success(codegenService.listColumnInfo(tableName));
    }

    @PostMapping("generate/{tableName}")
    public void generate(HttpServletResponse response, @PathVariable String tableName) {
        codegenService.generate(response, tableName);
    }

}
