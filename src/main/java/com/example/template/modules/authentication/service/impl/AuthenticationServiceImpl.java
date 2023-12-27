package com.example.template.modules.authentication.service.impl;

import com.example.template.modules.authentication.dto.AuthenticationDTO;
import com.example.template.modules.authentication.service.AuthenticationService;
import com.example.template.modules.authentication.service.TokenService;
import com.example.template.modules.system.dto.SystemUserDTO;
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
    public SystemUserDTO login(AuthenticationDTO authenticationDTO) {
        String username = authenticationDTO.getUsername();
        String password = authenticationDTO.getPassword();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            throw new RuntimeException("登录失败, 用户名或密码错误", e);
        }
        log.info("用户{}账号密码校验成功", username);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        if (!customUserDetails.getEnabled()) {
            throw new RuntimeException("用户账号未激活，请联系管理员");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SystemUserDTO systemUserDTO = new SystemUserDTO();
        BeanUtils.copyProperties(customUserDetails, systemUserDTO);
        String token = tokenService.createToken(systemUserDTO);
        systemUserDTO.setToken(token);
        return systemUserDTO;
    }

    @Override
    public void logout(String username) {

    }
}
