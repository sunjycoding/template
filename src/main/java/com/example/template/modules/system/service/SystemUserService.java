package com.example.template.modules.system.service;

import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.system.dto.SystemUserCriteriaDTO;
import com.example.template.modules.system.dto.SystemUserDTO;

import java.util.List;

/**
 * @author created by sunjy on 12/21/23
 */
public interface SystemUserService {

    Pagination<SystemUserDTO> page(PaginationRequest paginationRequest, SystemUserCriteriaDTO systemUserCriteriaDTO);

    List<SystemUserDTO> list();

    SystemUserDTO get(String id);

    SystemUserDTO getByUsername(String username);

    void create(SystemUserDTO systemUserDTO);

    void update(SystemUserDTO systemUserDTO);

    void delete(String id);

    void delete(List<String> idList);

}
