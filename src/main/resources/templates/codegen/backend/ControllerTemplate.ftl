package com.example.template.modules.${moduleLowerCase}.controller;

import com.example.template.common.data.HttpResult;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.${moduleLowerCase}.dto.${moduleUpperCase}${nameUpperCase}CriteriaDTO;
import com.example.template.modules.${moduleLowerCase}.dto.${moduleUpperCase}${nameUpperCase}DTO;
import com.example.template.modules.${moduleLowerCase}.service.${moduleUpperCase}${nameUpperCase}Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author created by ${author} on ${date}
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("${moduleLowerCase}/${namePlural}")
public class ${moduleUpperCase}${nameUpperCase}Controller {

    private final ${moduleUpperCase}${nameUpperCase}Service ${moduleLowerCase}${nameUpperCase}Service;

    @GetMapping("page")
    public HttpResult page(PaginationRequest paginationRequest, ${moduleUpperCase}${nameUpperCase}CriteriaDTO ${moduleLowerCase}${nameUpperCase}CriteriaDTO) {
        return HttpResult.success(${moduleLowerCase}${nameUpperCase}Service.page(paginationRequest, ${moduleLowerCase}${nameUpperCase}CriteriaDTO));
    }

    @GetMapping
    public HttpResult list() {
        return HttpResult.success(${moduleLowerCase}${nameUpperCase}Service.list());
    }

    @GetMapping("{id}")
    public HttpResult get(@PathVariable String id) {
        return HttpResult.success(${moduleLowerCase}${nameUpperCase}Service.get(id));
    }

    @PostMapping
    public HttpResult create(@RequestBody ${moduleUpperCase}${nameUpperCase}DTO ${moduleLowerCase}${nameUpperCase}DTO) {
        ${moduleLowerCase}${nameUpperCase}Service.create(${moduleLowerCase}${nameUpperCase}DTO);
        return HttpResult.success();
    }

    @PutMapping
    public HttpResult update(@RequestBody ${moduleUpperCase}${nameUpperCase}DTO ${moduleLowerCase}${nameUpperCase}DTO) {
        ${moduleLowerCase}${nameUpperCase}Service.update(${moduleLowerCase}${nameUpperCase}DTO);
        return HttpResult.success();
    }

    @DeleteMapping("{id}")
    public HttpResult delete(@PathVariable String id) {
        ${moduleLowerCase}${nameUpperCase}Service.delete(id);
        return HttpResult.success();
    }

    @DeleteMapping
    public HttpResult delete(@RequestBody List<String> idList) {
        ${moduleLowerCase}${nameUpperCase}Service.delete(idList);
        return HttpResult.success();
    }

}
