package com.example.template.modules.authentication.service;

import com.example.template.modules.authentication.dto.AuthenticationDTO;
import com.example.template.modules.user.dto.UserDTO;

/**
 * @author created by sunjy on 12/21/23
 */
public interface AuthenticationService {

    UserDTO login(AuthenticationDTO authenticationDTO);

    void logout(String username);

}
