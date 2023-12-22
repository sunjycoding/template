package com.example.template.common.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author created by sunjy on 12/21/23
 */
@Data
public abstract class BaseDTO {

    private String id;

    private String createdBy;

    private LocalDateTime createdDate;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;

}
