package com.example.template.modules.system.service;

import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.system.dto.SystemRoleCriteriaDTO;
import com.example.template.modules.system.dto.SystemRoleDTO;

import java.util.List;

/**
 * @author created by sunjy on 12/30/23
 */
public interface SystemRoleService {

    Pagination<SystemRoleDTO> page(PaginationRequest paginationRequest, SystemRoleCriteriaDTO systemRoleCriteriaDTO);

    List<SystemRoleDTO> list();

    SystemRoleDTO get(String id);

    void create(SystemRoleDTO systemRoleDTO);

    void update(SystemRoleDTO systemRoleDTO);

    void delete(String id);

    void delete(List<String> idList);

}
