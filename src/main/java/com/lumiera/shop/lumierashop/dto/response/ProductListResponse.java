package com.lumiera.shop.lumierashop.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductListResponse {

    private Long id;
    private String thumbnailUrl;
    private String type;
    private String name;
    private Integer price;
    private Integer stockQuantity;
    private LocalDateTime createdAt;
}
