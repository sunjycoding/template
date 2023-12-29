package com.example.template.modules.codegen.dto;

import lombok.Data;

/**
 * @author created by sunjy on 12/28/23
 */
@Data
public class CodegenTableColumnInfoDTO {

    private String name;

    private String type;

    private String comment;

    private Boolean nullable;

    private Boolean unique;

}
