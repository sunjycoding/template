package com.example.template.modules.codegen.service;

import com.example.template.common.data.Pagination;
import com.example.template.common.data.PaginationRequest;
import com.example.template.modules.codegen.dto.CodegenTableColumnInfoDTO;
import com.example.template.modules.codegen.dto.CodegenTableInfoCriteriaDTO;
import com.example.template.modules.codegen.dto.CodegenTableInfoDTO;

import java.util.List;

/**
 * @author created by sunjy on 12/28/23
 */
public interface CodegenService {

    Pagination<CodegenTableInfoDTO> page(PaginationRequest paginationRequest, CodegenTableInfoCriteriaDTO codegenTableInfoCriteriaDTO);

    List<CodegenTableInfoDTO> listTableInfo();

    List<CodegenTableColumnInfoDTO> listColumnInfo(String tableName);

}
