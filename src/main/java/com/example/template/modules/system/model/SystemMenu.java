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
@Table(name = "tb_system_menu")
public class SystemMenu extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column
    private String parentId;

    @Column(nullable = false)
    private String type;

    @Column
    private String path;

    @Column
    private String icon;

    @Column
    private String permissionTag;

    @Column
    private Integer orderNum;

    @Column(nullable = false)
    private Boolean enabled;

}