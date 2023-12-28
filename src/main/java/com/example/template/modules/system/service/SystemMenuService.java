package com.example.template.modules.system.service;

import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.system.dto.SystemMenuCriteriaDTO;
import com.example.template.modules.system.dto.SystemMenuDTO;

import java.util.List;

/**
 * @author created by sunjy on 12/21/23
 */
public interface SystemMenuService {

    Pagination<SystemMenuDTO> page(PaginationRequest paginationRequest, SystemMenuCriteriaDTO systemMenuCriteriaDTO);

    List<SystemMenuDTO> list();

    SystemMenuDTO get(String id);

    void create(SystemMenuDTO systemMenuDTO);

    void update(SystemMenuDTO systemMenuDTO);

    void delete(String id);

    void delete(List<String> idList);

}
