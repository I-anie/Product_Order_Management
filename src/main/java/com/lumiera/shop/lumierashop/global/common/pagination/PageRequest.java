package com.lumiera.shop.lumierashop.global.common.pagination;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PageRequest {

    private Integer page;
    private Integer size;

    public int getPageOrDefault() {
        return page == null ? 1 : page;
    }

    public int getSizeOrDefault() {
        return size == null ? 10 : size;
    }
}
