package com.example.template.modules.system.model;

import com.example.template.common.data.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${tableComment}
 *
 * @author created by ${author} on ${date}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "${tableName}")
public class ${moduleUpperCase}${nameUpperCase} extends BaseEntity {

    <#list propertyList as property>
    /**
     * ${property.comment}
     */
    <#if property.unique && property.nullable>
    @Column(unique = true)
    <#elseif property.unique && !property.nullable>
    @Column(unique = true, nullable = false)
    <#elseif !property.unique && property.nullable>
    @Column
    <#else>
    @Column(nullable = false)
    </#if>
    private ${property.type} ${property.name};
        
    </#list>
}