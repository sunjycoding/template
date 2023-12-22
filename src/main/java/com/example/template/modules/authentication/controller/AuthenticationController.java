package com.example.template.modules.authentication.controller;

import com.example.template.common.data.HttpResult;
import com.example.template.modules.authentication.dto.AuthenticationDTO;
import com.example.template.modules.authentication.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author created by sunjy on 12/21/23
 */
@AllArgsConstructor
@RestController
@RequestMapping("authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("login")
    public HttpResult login(@RequestBody AuthenticationDTO authenticationDTO) {
        return HttpResult.success(authenticationService.login(authenticationDTO));
    }

    @PostMapping("logout/{username}")
    public HttpResult logout(@PathVariable String username) {
        authenticationService.logout(username);
        return HttpResult.success();
    }

}
