package com.example.template.modules.authentication.service.impl;

import com.example.template.modules.authentication.dto.AuthenticationDTO;
import com.example.template.modules.authentication.service.AuthenticationService;
import com.example.template.modules.authentication.service.TokenService;
import com.example.template.modules.user.dto.UserDTO;
import com.example.template.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author created by sunjy on 12/21/23
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @Override
    public UserDTO login(AuthenticationDTO authenticationDTO) {
        String username = authenticationDTO.getUsername();
        String password = authenticationDTO.getPassword();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            throw new RuntimeException("登录失败", e);
        }
        log.info("用户{}登录成功", username);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDTO userDTO = new UserDTO();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        BeanUtils.copyProperties(customUserDetails, userDTO);
        String token = tokenService.createToken(userDTO);
        userDTO.setToken(token);
        return userDTO;
    }

    @Override
    public void logout(String username) {

    }
}
