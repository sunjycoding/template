package com.example.template.modules.authentication.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.template.modules.system.dto.SystemUserDTO;

/**
 * @author created by sunjy on 12/21/23
 */
public interface TokenService {

    String createToken(SystemUserDTO systemUserDTO);

    DecodedJWT verifyToken(String token);

}
