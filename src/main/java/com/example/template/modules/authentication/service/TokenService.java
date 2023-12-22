package com.example.template.modules.authentication.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.template.modules.user.dto.UserDTO;

/**
 * @author created by sunjy on 12/21/23
 */
public interface TokenService {

    String createToken(UserDTO userDTO);

    DecodedJWT verifyToken(String token);

}
