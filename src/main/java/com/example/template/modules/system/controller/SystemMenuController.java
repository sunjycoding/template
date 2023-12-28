package com.example.template.modules.system.controller;

import com.example.template.common.data.HttpResult;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.system.dto.SystemMenuCriteriaDTO;
import com.example.template.modules.system.dto.SystemMenuDTO;
import com.example.template.modules.system.service.SystemMenuService;
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
@RequestMapping("system/menus")
public class SystemMenuController {

    private final SystemMenuService systemMenuService;

    @GetMapping("page")
    public HttpResult page(PaginationRequest paginationRequest, SystemMenuCriteriaDTO systemMenuCriteriaDTO) {
        return HttpResult.success(systemMenuService.page(paginationRequest, systemMenuCriteriaDTO));
    }

    @GetMapping
    public HttpResult list() {
        return HttpResult.success(systemMenuService.list());
    }

    @GetMapping("{id}")
    public HttpResult get(@PathVariable String id) {
        return HttpResult.success(systemMenuService.get(id));
    }

    @PostMapping
    public HttpResult create(@RequestBody SystemMenuDTO systemMenuDTO) {
        systemMenuService.create(systemMenuDTO);
        return HttpResult.success();
    }

    @PutMapping
    public HttpResult update(@RequestBody SystemMenuDTO systemMenuDTO) {
        systemMenuService.update(systemMenuDTO);
        return HttpResult.success();
    }

    @DeleteMapping("{id}")
    public HttpResult delete(@PathVariable String id) {
        systemMenuService.delete(id);
        return HttpResult.success();
    }

    @DeleteMapping
    public HttpResult delete(@RequestBody List<String> idList) {
        systemMenuService.delete(idList);
        return HttpResult.success();
    }

}
