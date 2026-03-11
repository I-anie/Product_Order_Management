package com.lumiera.shop.lumierashop.mapper;

import com.lumiera.shop.lumierashop.domain.Order;
import com.lumiera.shop.lumierashop.domain.enums.OrderStatus;
import com.lumiera.shop.lumierashop.dto.response.OrderResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminOrderMapper {
    List<Order> findAll(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("status") OrderStatus status,
            @Param("username") String username,
            @Param("deleted") boolean deleted
    );

    OrderResponse findById(@Param("orderId") Long orderId);

    int count(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("status") OrderStatus status,
            @Param("username") String username,
            @Param("deleted") boolean deleted
    );

    int updateStatus(@Param("orderIds") List<Long> orderIds);
}
