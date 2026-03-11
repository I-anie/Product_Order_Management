package com.lumiera.shop.lumierashop.dto.request;

import com.lumiera.shop.lumierashop.global.common.pagination.PageRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductSearchCondition extends PageRequest {

    private String keyword;
    private boolean deleted;
    private Long categoryId;
}
