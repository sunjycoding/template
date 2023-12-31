package com.example.template.modules.${moduleLowerCase}.service.impl;

import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.${moduleLowerCase}.dto.${moduleUpperCase}${nameUpperCase}CriteriaDTO;
import com.example.template.modules.${moduleLowerCase}.dto.${moduleUpperCase}${nameUpperCase}DTO;
import com.example.template.modules.${moduleLowerCase}.model.${moduleUpperCase}${nameUpperCase};
import com.example.template.modules.${moduleLowerCase}.repository.${moduleUpperCase}${nameUpperCase}Repository;
import com.example.template.modules.${moduleLowerCase}.service.${moduleUpperCase}${nameUpperCase}Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author created by ${author} on ${date}
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ${moduleUpperCase}${nameUpperCase}ServiceImpl implements ${moduleUpperCase}${nameUpperCase}Service {

    private final ${moduleUpperCase}${nameUpperCase}Repository ${moduleLowerCase}${nameUpperCase}Repository;

    @Override
    public Pagination<${moduleUpperCase}${nameUpperCase}DTO> page(PaginationRequest paginationRequest, ${moduleUpperCase}${nameUpperCase}CriteriaDTO ${moduleLowerCase}${nameUpperCase}CriteriaDTO) {
        Page<${moduleUpperCase}${nameUpperCase}> ${nameLowerCase}Page = ${moduleLowerCase}${nameUpperCase}Repository.findAll(paginationRequest.toPageable());
        Page<${moduleUpperCase}${nameUpperCase}DTO> ${nameLowerCase}DtoPage = ${nameLowerCase}Page.map(this::convertToDto);
        return Pagination.form(${nameLowerCase}DtoPage);
    }

    @Override
    public List<${moduleUpperCase}${nameUpperCase}DTO> list() {
        return convertToDtoList(${moduleLowerCase}${nameUpperCase}Repository.findAll());
    }

    @Override
    public ${moduleUpperCase}${nameUpperCase}DTO get(String id) {
        return convertToDto(${moduleLowerCase}${nameUpperCase}Repository.findById(id).orElse(null));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(${moduleUpperCase}${nameUpperCase}DTO ${moduleLowerCase}${nameUpperCase}DTO) {
        ${moduleUpperCase}${nameUpperCase} ${moduleLowerCase}${nameUpperCase} = convertToEntity(${moduleLowerCase}${nameUpperCase}DTO);
        ${moduleLowerCase}${nameUpperCase}Repository.save(${moduleLowerCase}${nameUpperCase});
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(${moduleUpperCase}${nameUpperCase}DTO ${moduleLowerCase}${nameUpperCase}DTO) {
        ${moduleUpperCase}${nameUpperCase} ${moduleLowerCase}${nameUpperCase} = convertToEntity(${moduleLowerCase}${nameUpperCase}DTO);
        ${moduleLowerCase}${nameUpperCase}Repository.save(${moduleLowerCase}${nameUpperCase});
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        ${moduleLowerCase}${nameUpperCase}Repository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<String> idList) {
        ${moduleLowerCase}${nameUpperCase}Repository.deleteAllById(idList);
    }

    private ${moduleUpperCase}${nameUpperCase}DTO convertToDto(${moduleUpperCase}${nameUpperCase} ${moduleLowerCase}${nameUpperCase}) {
        ${moduleUpperCase}${nameUpperCase}DTO ${moduleLowerCase}${nameUpperCase}DTO = new ${moduleUpperCase}${nameUpperCase}DTO();
        BeanUtils.copyProperties(${moduleLowerCase}${nameUpperCase}, ${moduleLowerCase}${nameUpperCase}DTO);
        return ${moduleLowerCase}${nameUpperCase}DTO;
    }

    private List<${moduleUpperCase}${nameUpperCase}DTO> convertToDtoList(List<${moduleUpperCase}${nameUpperCase}> ${moduleLowerCase}${nameUpperCase}List) {
        List<${moduleUpperCase}${nameUpperCase}DTO> ${moduleLowerCase}${nameUpperCase}DTOList = new ArrayList<>();
        ${moduleLowerCase}${nameUpperCase}List.forEach(${moduleLowerCase}${nameUpperCase} -> {
            ${moduleLowerCase}${nameUpperCase}DTOList.add(convertToDto(${moduleLowerCase}${nameUpperCase}));
        });
        return ${moduleLowerCase}${nameUpperCase}DTOList;
    }

    private ${moduleUpperCase}${nameUpperCase} convertToEntity(${moduleUpperCase}${nameUpperCase}DTO ${moduleLowerCase}${nameUpperCase}DTO) {
        ${moduleUpperCase}${nameUpperCase} ${moduleLowerCase}${nameUpperCase} = new ${moduleUpperCase}${nameUpperCase}();
        BeanUtils.copyProperties(${moduleLowerCase}${nameUpperCase}DTO, ${moduleLowerCase}${nameUpperCase});
        return ${moduleLowerCase}${nameUpperCase};
    }

}
