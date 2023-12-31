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
public class ${moduleUpperCase}${nameUpperCase}DTO extends BaseDTO {

<#list propertyList as property>
    /**
    * ${property.comment}
    */
    private ${property.type} ${property.name};

</#list>
}