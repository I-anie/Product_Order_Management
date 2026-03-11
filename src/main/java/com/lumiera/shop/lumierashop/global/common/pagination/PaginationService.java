package com.lumiera.shop.lumierashop.global.common.pagination;

import org.springframework.stereotype.Service;

@Service
public class PaginationService {

    public PageHandler of(int page, int size, int totalCount) {
        return new PageHandler(page, size, totalCount);
    }
}