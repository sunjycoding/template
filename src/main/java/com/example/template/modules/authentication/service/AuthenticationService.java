package com.example.template.modules.authentication.service;

import com.example.template.modules.authentication.dto.AuthenticationDTO;
import com.example.template.modules.system.dto.SystemUserDTO;

/**
 * @author created by sunjy on 12/21/23
 */
public interface AuthenticationService {

    SystemUserDTO login(AuthenticationDTO authenticationDTO);

    void logout(String username);

}
