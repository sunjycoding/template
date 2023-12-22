package com.example.template.modules.user.dto;

import com.example.template.common.data.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author created by sunjy on 12/21/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCreateDTO extends BaseDTO {

    private String username;

    private String password;

    private String name;

    private String avatarUrl;

    private String token;

}
