package com.example.template.modules.system.service.impl;

import com.example.template.common.constants.AppConstants;
import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.system.dto.SystemUserCriteriaDTO;
import com.example.template.modules.system.dto.SystemUserDTO;
import com.example.template.modules.system.model.SystemUser;
import com.example.template.modules.system.repository.SystemUserRepository;
import com.example.template.modules.system.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author created by sunjy on 12/21/23
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepository systemUserRepository;

    @Override
    public Pagination<SystemUserDTO> page(PaginationRequest paginationRequest, SystemUserCriteriaDTO systemUserCriteriaDTO) {
        SystemUser exampleSystemUser = new SystemUser();
        String name = systemUserCriteriaDTO.getName();
        if (StringUtils.hasText(name)) {
            exampleSystemUser.setName(name);
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<SystemUser> example = Example.of(exampleSystemUser, exampleMatcher);
        Page<SystemUser> userPage = systemUserRepository.findAll(example, paginationRequest.toPageable());
        Page<SystemUserDTO> userDtoPage = userPage.map(this::convertToDto);
        return Pagination.form(userDtoPage);
    }

    @Override
    public List<SystemUserDTO> list() {
        return convertToDtoList(systemUserRepository.findAll());
    }

    @Override
    public SystemUserDTO get(String id) {
        return null;
    }

    @Override
    public SystemUserDTO getByUsername(String username) {
        SystemUser systemUser = systemUserRepository.findUserByUsername(username).orElse(null);
        return convertToDto(systemUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(SystemUserDTO systemUserDTO) {
        String username = systemUserDTO.getUsername();
        SystemUser selectedSystemUser = systemUserRepository.findUserByUsername(username).orElse(null);
        if (selectedSystemUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        SystemUser systemUser = convertToEntity(systemUserDTO);
        // 分配默认密码并加密
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(AppConstants.DEFAULT_PASSWORD);
        systemUser.setPassword(encodedPassword);
        systemUserRepository.save(systemUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(SystemUserDTO systemUserDTO) {
        if (AppConstants.ADMIN_ID.equals(systemUserDTO.getId())) {
            throw new RuntimeException("无法修改超级管理员");
        }
        SystemUser systemUser = convertToEntity(systemUserDTO);
        systemUserRepository.save(systemUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        if (AppConstants.ADMIN_ID.equals(id)) {
            throw new RuntimeException("无法删除超级管理员");
        }
        systemUserRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<String> idList) {
        if (idList.contains(AppConstants.ADMIN_ID)) {
            throw new RuntimeException("无法删除超级管理员");
        }
        systemUserRepository.deleteAllById(idList);
    }

    private SystemUserDTO convertToDto(SystemUser systemUser) {
        SystemUserDTO systemUserDTO = new SystemUserDTO();
        BeanUtils.copyProperties(systemUser, systemUserDTO);
        return systemUserDTO;
    }

    private List<SystemUserDTO> convertToDtoList(List<SystemUser> systemUserList) {
        List<SystemUserDTO> systemUserDTOList = new ArrayList<>();
        systemUserList.forEach(systemUser -> {
            systemUserDTOList.add(convertToDto(systemUser));
        });
        return systemUserDTOList;
    }

    private SystemUser convertToEntity(SystemUserDTO systemUserDTO) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserDTO, systemUser);
        return systemUser;
    }

}
