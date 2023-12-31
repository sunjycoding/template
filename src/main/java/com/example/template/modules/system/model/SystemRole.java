package com.example.template.modules.system.model;

import com.example.template.common.data.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表
 *
 * @author created by sunjy on 12/30/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_system_role")
public class SystemRole extends BaseEntity {

    /**
     * 角色名称
     */
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * 角色描述
     */
    @Column
    private String description;

}