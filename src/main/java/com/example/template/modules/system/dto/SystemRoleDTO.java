package com.example.template.modules.system.dto;

import com.example.template.common.data.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* 角色表
*
* @author created by sunjy on 12/30/23
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class SystemRoleDTO extends BaseDTO {

    /**
    * 角色名称
    */
    private String name;

    /**
    * 角色描述
    */
    private String description;

}