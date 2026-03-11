package com.lumiera.shop.lumierashop.dto.request;

import com.lumiera.shop.lumierashop.domain.enums.OrderStatus;
import com.lumiera.shop.lumierashop.global.common.pagination.PageRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderSearchCondition extends PageRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private OrderStatus status;
    private String username;
    private boolean deleted;

    public LocalDateTime getStartDateTimeOrNull() {
        return startDate == null ? null : startDate.atStartOfDay();
    }

    public LocalDateTime getEndDateTimeOrNull() {
        return endDate == null ? null : endDate.plusDays(1).atStartOfDay();
    }
}
