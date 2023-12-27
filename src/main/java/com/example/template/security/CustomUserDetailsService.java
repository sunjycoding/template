package com.example.template.security;

import com.example.template.modules.system.model.SystemUser;
import com.example.template.modules.system.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author created by sunjy on 12/21/23
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SystemUserRepository systemUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserRepository.findUserByUsername(username).orElse(null);
        if (systemUser == null) {
            throw new UsernameNotFoundException(username);
        }
        CustomUserDetails customUserDetails = new CustomUserDetails();
        BeanUtils.copyProperties(systemUser, customUserDetails);
        return customUserDetails;
    }

}
