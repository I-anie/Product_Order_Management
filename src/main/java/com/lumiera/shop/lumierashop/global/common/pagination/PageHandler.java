package com.lumiera.shop.lumierashop.global.common.pagination;

import lombok.Getter;

@Getter
public class PageHandler {

    private final int page;
    private final int size;

    private final int offset;
    private final int limit;
    private final int totalCount;

    private final int navSize;
    private final int totalPages;
    private final int startPage;
    private final int endPage;
    private final boolean hasPrev;
    private final boolean hasNext;

    public PageHandler(int page, int size, int totalCount) {
        this.page = Math.max(page, 1);
        this.size = clamp(size, 10, 30);
        this.totalCount = Math.max(totalCount, 0);

        this.offset = (this.page - 1) * this.size;
        this.limit = this.size;

        this.navSize = 10;
        this.totalPages = (int) Math.ceil((double) this.totalCount / this.size);
        this.startPage = ((this.page - 1) / navSize) * navSize + 1;
        this.endPage = Math.min(this.startPage + navSize - 1, Math.max(this.totalPages, 1));
        this.hasPrev = this.startPage > 1;
        this.hasNext = this.endPage < this.totalPages;
    }

    private int clamp(int size, int min, int max) {
        return Math.max(min, Math.min(max, size));
    }
}
