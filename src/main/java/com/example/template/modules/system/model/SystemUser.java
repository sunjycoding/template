package com.example.template.modules.system.model;

import com.example.template.common.data.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author created by sunjy on 12/21/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_system_user")
public class SystemUser extends BaseEntity {

    @Column(unique = true, updatable = false)
    private String username;

    @Column(nullable = false, updatable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column
    private String gender;

    @Column
    private String phone;

    @Column(nullable = false)
    private Boolean enabled;

}