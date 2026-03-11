package com.lumiera.shop.lumierashop.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeleteProductForm {

    @NotEmpty(message = "상품을 선택해주세요.")
    private List<Long> productIds;
}
