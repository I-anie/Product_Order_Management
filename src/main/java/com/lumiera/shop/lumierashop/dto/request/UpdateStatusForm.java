package com.lumiera.shop.lumierashop.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStatusForm {

    @NotEmpty(message = "주문을 선택해주세요.")
    private List<Long> orderIds;
}
