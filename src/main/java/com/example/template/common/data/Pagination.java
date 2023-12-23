package com.example.template.common.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author created by sunjy on 12/22/23
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pagination<T> {

    private Integer pageNumber;

    private Integer pageSize;

    private Long totalElements;

    private Integer totalPages;

    private List<T> content;

    public static <T> Pagination<T> form(Page<T> page) {
        // JPA分页默认从0开始，所以+1
        return new Pagination<>(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent()
        );
    }

}
