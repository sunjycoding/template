package com.example.template.modules.user.controller;

import com.example.template.common.data.HttpResult;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.user.dto.UserCriteriaDTO;
import com.example.template.modules.user.dto.UserDTO;
import com.example.template.modules.user.service.UserService;
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
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("page")
    public HttpResult page(PaginationRequest paginationRequest, UserCriteriaDTO userCriteriaDTO) {
        return HttpResult.success(userService.page(paginationRequest, userCriteriaDTO));
    }

    @GetMapping
    public HttpResult list() {
        return HttpResult.success(userService.list());
    }

    @GetMapping("{id}")
    public HttpResult get(@PathVariable String id) {
        return HttpResult.success(userService.get(id));
    }

    @PostMapping
    public HttpResult create(@RequestBody UserDTO userDTO) {
        userService.create(userDTO);
        return HttpResult.success();
    }

    @PutMapping
    public HttpResult update(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
        return HttpResult.success();
    }

    @DeleteMapping("{id}")
    public HttpResult delete(@PathVariable String id) {
        userService.delete(id);
        return HttpResult.success();
    }

    @DeleteMapping
    public HttpResult delete(@RequestBody List<String> idList) {
        userService.delete(idList);
        return HttpResult.success();
    }

}
