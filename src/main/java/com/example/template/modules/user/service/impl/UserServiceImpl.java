package com.example.template.modules.user.service.impl;

import com.example.template.common.constants.AppConstants;
import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.user.dto.UserCriteriaDTO;
import com.example.template.modules.user.dto.UserDTO;
import com.example.template.modules.user.model.User;
import com.example.template.modules.user.repository.UserRepository;
import com.example.template.modules.user.service.UserService;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Pagination<UserDTO> page(PaginationRequest paginationRequest, UserCriteriaDTO userCriteriaDTO) {
        User exampleUser = new User();
        String name = userCriteriaDTO.getName();
        if (StringUtils.hasText(name)) {
            exampleUser.setName(name);
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<User> example = Example.of(exampleUser, exampleMatcher);
        Page<User> userPage = userRepository.findAll(example, paginationRequest.toPageable());
        Page<UserDTO> userDtoPage = userPage.map(this::convertToDto);
        return Pagination.form(userDtoPage);
    }

    @Override
    public List<UserDTO> list() {
        return convertToDtoList(userRepository.findAll());
    }

    @Override
    public UserDTO get(String id) {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(UserDTO userDTO) {
        String username = userDTO.getUsername();
        User selectedUser = userRepository.findUserByUsername(username).orElse(null);
        if (selectedUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        User user = convertToEntity(userDTO);
        // 分配默认密码并加密
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(AppConstants.DEFAULT_PASSWORD);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        if (AppConstants.ADMIN_ID.equals(id)) {
            throw new RuntimeException("无法删除管理员");
        }
        userRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<String> idList) {
        if (idList.contains(AppConstants.ADMIN_ID)) {
            throw new RuntimeException("无法删除管理员");
        }
        userRepository.deleteAllById(idList);
    }

    private UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    private List<UserDTO> convertToDtoList(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> {
            userDTOList.add(convertToDto(user));
        });
        return userDTOList;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

}
