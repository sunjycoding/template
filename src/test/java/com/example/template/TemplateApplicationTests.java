package com.example.template;

import com.example.template.modules.system.repository.SystemUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TemplateApplicationTests {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
    }
}
