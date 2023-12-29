package com.example.template.common.service;

import java.util.Map;

/**
 * @author created by sunjy on 12/28/23
 */
public interface FreemarkerTemplateService {

    String renderTemplate(String templateName, Map<String, Object> model);

}
