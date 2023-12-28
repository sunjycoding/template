package com.example.template.modules.system.service.impl;

import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.common.util.TreeUtils;
import com.example.template.modules.system.dto.SystemMenuCriteriaDTO;
import com.example.template.modules.system.dto.SystemMenuDTO;
import com.example.template.modules.system.model.SystemMenu;
import com.example.template.modules.system.repository.SystemMenuRepository;
import com.example.template.modules.system.service.SystemMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
public class SystemMenuServiceImpl implements SystemMenuService {

    private final SystemMenuRepository systemMenuRepository;

    @Override
    public Pagination<SystemMenuDTO> page(PaginationRequest paginationRequest, SystemMenuCriteriaDTO systemMenuCriteriaDTO) {
        SystemMenu exampleSystemMenu = new SystemMenu();
        String name = systemMenuCriteriaDTO.getName();
        if (StringUtils.hasText(name)) {
            exampleSystemMenu.setName(name);
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<SystemMenu> example = Example.of(exampleSystemMenu, exampleMatcher);
        Page<SystemMenu> userPage = systemMenuRepository.findAll(example, paginationRequest.toPageable());
        Page<SystemMenuDTO> userDtoPage = userPage.map(this::convertToDto);
        return Pagination.form(userDtoPage);
    }

    @Override
    public List<SystemMenuDTO> list() {
        List<SystemMenuDTO> systemMenuDTOList = convertToDtoList(systemMenuRepository.findAll(Sort.by("orderNum").ascending()));
        return TreeUtils.buildTree(systemMenuDTOList, "id", "parentId", "children");
    }

    @Override
    public SystemMenuDTO get(String id) {
        return convertToDto(systemMenuRepository.findById(id).orElse(null));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(SystemMenuDTO systemMenuDTO) {
        SystemMenu systemMenu = convertToEntity(systemMenuDTO);
        systemMenuRepository.save(systemMenu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(SystemMenuDTO systemMenuDTO) {
        SystemMenu systemMenu = convertToEntity(systemMenuDTO);
        systemMenuRepository.save(systemMenu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        systemMenuRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<String> idList) {
        systemMenuRepository.deleteAllById(idList);
    }

    private SystemMenuDTO convertToDto(SystemMenu systemMenu) {
        SystemMenuDTO systemMenuDTO = new SystemMenuDTO();
        BeanUtils.copyProperties(systemMenu, systemMenuDTO);
        return systemMenuDTO;
    }

    private List<SystemMenuDTO> convertToDtoList(List<SystemMenu> systemMenuList) {
        List<SystemMenuDTO> systemMenuDTOList = new ArrayList<>();
        systemMenuList.forEach(systemMenu -> {
            systemMenuDTOList.add(convertToDto(systemMenu));
        });
        return systemMenuDTOList;
    }

    private SystemMenu convertToEntity(SystemMenuDTO systemMenuDTO) {
        SystemMenu systemMenu = new SystemMenu();
        BeanUtils.copyProperties(systemMenuDTO, systemMenu);
        return systemMenu;
    }

}
