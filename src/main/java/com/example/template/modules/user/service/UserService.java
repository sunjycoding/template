package com.example.template.modules.user.service;

import com.example.template.modules.user.dto.UserCreateDTO;
import com.example.template.modules.user.dto.UserCriteriaDTO;
import com.example.template.modules.user.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author created by sunjy on 12/21/23
 */
public interface UserService {

    Page<UserDTO> page(Pageable pageable, UserCriteriaDTO userCriteriaDTO);

    List<UserDTO> list();

    UserDTO get(String id);

    void create(UserCreateDTO userCreateDTO);

    void update(UserDTO userDTO);

    void delete(String id);

    void delete(List<String> idList);

}
