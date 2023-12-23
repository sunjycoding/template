package com.example.template.common.data;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author created by sunjy on 12/22/23
 */
@Data
public class PaginationRequest {

    private Integer pageNumber;

    private Integer pageSize;

    public Pageable toPageable() {
        // 默认按更新时间排序
        Sort sort = Sort.by("lastModifiedDate").descending();
        // JPA分页默认从0开始，所以-1
        return PageRequest.of(pageNumber - 1, pageSize, sort);
    }

    public Pageable toPageable(Sort sort) {
        return PageRequest.of(pageNumber - 1, pageSize, sort);
    }

}
