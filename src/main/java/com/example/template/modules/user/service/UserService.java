package com.example.template.modules.user.service;

import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.user.dto.UserCriteriaDTO;
import com.example.template.modules.user.dto.UserDTO;

import java.util.List;

/**
 * @author created by sunjy on 12/21/23
 */
public interface UserService {

    Pagination<UserDTO> page(PaginationRequest paginationRequest, UserCriteriaDTO userCriteriaDTO);

    List<UserDTO> list();

    UserDTO get(String id);

    void create(UserDTO userDTO);

    void update(UserDTO userDTO);

    void delete(String id);

    void delete(List<String> idList);

}
