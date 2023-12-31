package com.example.template.modules.system.service.impl;

import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.system.dto.SystemRoleCriteriaDTO;
import com.example.template.modules.system.dto.SystemRoleDTO;
import com.example.template.modules.system.model.SystemRole;
import com.example.template.modules.system.repository.SystemRoleRepository;
import com.example.template.modules.system.service.SystemRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author created by sunjy on 12/30/23
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SystemRoleServiceImpl implements SystemRoleService {

    private final SystemRoleRepository systemRoleRepository;

    @Override
    public Pagination<SystemRoleDTO> page(PaginationRequest paginationRequest, SystemRoleCriteriaDTO systemRoleCriteriaDTO) {
        Page<SystemRole> rolePage = systemRoleRepository.findAll(paginationRequest.toPageable());
        Page<SystemRoleDTO> roleDtoPage = rolePage.map(this::convertToDto);
        return Pagination.form(roleDtoPage);
    }

    @Override
    public List<SystemRoleDTO> list() {
        return convertToDtoList(systemRoleRepository.findAll());
    }

    @Override
    public SystemRoleDTO get(String id) {
        return convertToDto(systemRoleRepository.findById(id).orElse(null));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(SystemRoleDTO systemRoleDTO) {
        SystemRole systemRole = convertToEntity(systemRoleDTO);
        systemRoleRepository.save(systemRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(SystemRoleDTO systemRoleDTO) {
        SystemRole systemRole = convertToEntity(systemRoleDTO);
        systemRoleRepository.save(systemRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        systemRoleRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<String> idList) {
        systemRoleRepository.deleteAllById(idList);
    }

    private SystemRoleDTO convertToDto(SystemRole systemRole) {
        SystemRoleDTO systemRoleDTO = new SystemRoleDTO();
        BeanUtils.copyProperties(systemRole, systemRoleDTO);
        return systemRoleDTO;
    }

    private List<SystemRoleDTO> convertToDtoList(List<SystemRole> systemRoleList) {
        List<SystemRoleDTO> systemRoleDTOList = new ArrayList<>();
        systemRoleList.forEach(systemRole -> {
            systemRoleDTOList.add(convertToDto(systemRole));
        });
        return systemRoleDTOList;
    }

    private SystemRole convertToEntity(SystemRoleDTO systemRoleDTO) {
        SystemRole systemRole = new SystemRole();
        BeanUtils.copyProperties(systemRoleDTO, systemRole);
        return systemRole;
    }

}
