package com.example.template.modules.${moduleLowerCase}.service;

import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.${moduleLowerCase}.dto.${moduleUpperCase}${nameUpperCase}CriteriaDTO;
import com.example.template.modules.${moduleLowerCase}.dto.${moduleUpperCase}${nameUpperCase}DTO;

import java.util.List;

/**
 * @author created by ${author} on ${date}
 */
public interface ${moduleUpperCase}${nameUpperCase}Service {

    Pagination<${moduleUpperCase}${nameUpperCase}DTO> page(PaginationRequest paginationRequest, ${moduleUpperCase}${nameUpperCase}CriteriaDTO ${moduleLowerCase}${nameUpperCase}CriteriaDTO);

    List<${moduleUpperCase}${nameUpperCase}DTO> list();

    ${moduleUpperCase}${nameUpperCase}DTO get(String id);

    void create(${moduleUpperCase}${nameUpperCase}DTO ${moduleLowerCase}${nameUpperCase}DTO);

    void update(${moduleUpperCase}${nameUpperCase}DTO ${moduleLowerCase}${nameUpperCase}DTO);

    void delete(String id);

    void delete(List<String> idList);

}
