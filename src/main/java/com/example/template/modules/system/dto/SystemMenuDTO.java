package com.example.template.modules.system.dto;

import com.example.template.common.data.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author created by sunjy on 12/21/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SystemMenuDTO extends BaseDTO {

    private String name;

    private String parentId;

    private String type;

    private String path;

    private String icon;

    private String permissionTag;

    private Integer orderNum;

    private Boolean enabled;

    private List<SystemMenuDTO> children;

}