package com.example.template.modules.system.controller;

import com.example.template.common.data.HttpResult;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.system.dto.SystemRoleCriteriaDTO;
import com.example.template.modules.system.dto.SystemRoleDTO;
import com.example.template.modules.system.service.SystemRoleService;
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
 * @author created by sunjy on 12/30/23
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("system/roles")
public class SystemRoleController {

    private final SystemRoleService systemRoleService;

    @GetMapping("page")
    public HttpResult page(PaginationRequest paginationRequest, SystemRoleCriteriaDTO systemRoleCriteriaDTO) {
        return HttpResult.success(systemRoleService.page(paginationRequest, systemRoleCriteriaDTO));
    }

    @GetMapping
    public HttpResult list() {
        return HttpResult.success(systemRoleService.list());
    }

    @GetMapping("{id}")
    public HttpResult get(@PathVariable String id) {
        return HttpResult.success(systemRoleService.get(id));
    }

    @PostMapping
    public HttpResult create(@RequestBody SystemRoleDTO systemRoleDTO) {
        systemRoleService.create(systemRoleDTO);
        return HttpResult.success();
    }

    @PutMapping
    public HttpResult update(@RequestBody SystemRoleDTO systemRoleDTO) {
        systemRoleService.update(systemRoleDTO);
        return HttpResult.success();
    }

    @DeleteMapping("{id}")
    public HttpResult delete(@PathVariable String id) {
        systemRoleService.delete(id);
        return HttpResult.success();
    }

    @DeleteMapping
    public HttpResult delete(@RequestBody List<String> idList) {
        systemRoleService.delete(idList);
        return HttpResult.success();
    }

}
