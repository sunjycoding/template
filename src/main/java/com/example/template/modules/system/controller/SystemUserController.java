package com.example.template.modules.system.controller;

import com.example.template.common.data.HttpResult;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.system.dto.SystemUserCriteriaDTO;
import com.example.template.modules.system.dto.SystemUserDTO;
import com.example.template.modules.system.service.SystemUserService;
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
 * @author created by sunjy on 12/21/23
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("system/users")
public class SystemUserController {

    private final SystemUserService systemUserService;

    @GetMapping("page")
    public HttpResult page(PaginationRequest paginationRequest, SystemUserCriteriaDTO systemUserCriteriaDTO) {
        return HttpResult.success(systemUserService.page(paginationRequest, systemUserCriteriaDTO));
    }

    @GetMapping
    public HttpResult list() {
        return HttpResult.success(systemUserService.list());
    }

    @GetMapping("{id}")
    public HttpResult get(@PathVariable String id) {
        return HttpResult.success(systemUserService.get(id));
    }

    @PostMapping
    public HttpResult create(@RequestBody SystemUserDTO systemUserDTO) {
        systemUserService.create(systemUserDTO);
        return HttpResult.success();
    }

    @PutMapping
    public HttpResult update(@RequestBody SystemUserDTO systemUserDTO) {
        systemUserService.update(systemUserDTO);
        return HttpResult.success();
    }

    @DeleteMapping("{id}")
    public HttpResult delete(@PathVariable String id) {
        systemUserService.delete(id);
        return HttpResult.success();
    }

    @DeleteMapping
    public HttpResult delete(@RequestBody List<String> idList) {
        systemUserService.delete(idList);
        return HttpResult.success();
    }

}
