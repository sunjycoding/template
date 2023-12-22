package com.example.template.modules.authentication.controller;

import com.example.template.common.data.HttpResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author created by sunjy on 12/21/23
 */
@AllArgsConstructor
@RestController
@RequestMapping("health")
public class HealthController {

    @GetMapping("hello")
    public HttpResult hello() {
        return HttpResult.success("Hello World");
    }

}
