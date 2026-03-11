package com.lumiera.shop.lumierashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailImage {

    private Long id;
    private Long productId;
    private String imageUrl;
    private int sortOrder;

    public ProductDetailImage(Long productId, String imageUrl, int sortOrder) {
        this.productId = productId;
        this.imageUrl = imageUrl;
        this.sortOrder = sortOrder;
    }
}
