package com.example.template.modules.system.dto;

import com.example.template.common.data.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author created by sunjy on 12/21/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SystemUserDTO extends BaseDTO {

    private String username;

    private String name;

    private String gender;

    private String genderValue;

    private String phone;

    private Boolean enabled;

    private String statusValue;

    private String token;

}