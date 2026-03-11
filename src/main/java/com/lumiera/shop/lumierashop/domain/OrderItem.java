package com.lumiera.shop.lumierashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    private Long id;
    private Long orderId;
    private Integer price;
    private Long productId;
    private Integer quantity;

    public OrderItem(Long orderId, Integer price, Long productId, Integer quantity) {
        this.orderId = orderId;
        this.price = price;
        this.productId = productId;
        this.quantity = quantity;
    }
}
