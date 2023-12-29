package com.example.template.common.service.impl;

import com.example.template.common.service.FreemarkerTemplateService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Map;

/**
 * @author created by sunjy on 12/28/23
 */
@RequiredArgsConstructor
@Service
public class FreemarkerTemplateServiceImpl implements FreemarkerTemplateService {

    private final Configuration configuration;

    @Override
    public String renderTemplate(String templateName, Map<String, Object> model) {
        try {
            Template template = configuration.getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            throw new RuntimeException("生成模版时出现错误", e);
        }
    }

}
