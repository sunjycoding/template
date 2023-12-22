package com.example.template.modules.user.service.impl;

import com.example.template.modules.user.dto.UserCreateDTO;
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
import org.springframework.data.domain.Pageable;
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
    public Page<UserDTO> page(Pageable pageable, UserCriteriaDTO userCriteriaDTO) {
        User exampleUser = new User();
        String name = userCriteriaDTO.getName();
        if (StringUtils.hasText(name)) {
            exampleUser.setName(name);
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<User> example = Example.of(exampleUser, exampleMatcher);
        Page<User> userPage = userRepository.findAll(example, pageable);
        return userPage.map(this::convertToDto);
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
    public void create(UserCreateDTO userCreateDTO) {
        User user = convertToEntity(userCreateDTO);
        userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UserDTO userDTO) {

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<String> idList) {

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

    private User convertToEntity(UserCreateDTO userCreateDTO) {
        User user = new User();
        BeanUtils.copyProperties(userCreateDTO, user);
        return user;
    }

}
