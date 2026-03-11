package com.lumiera.shop.lumierashop.mapper;

import com.lumiera.shop.lumierashop.domain.Order;
import com.lumiera.shop.lumierashop.domain.enums.OrderStatus;
import com.lumiera.shop.lumierashop.dto.response.OrderResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderMapper {
    List<Order> findAllAndDeletedAtIsNull(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("status") OrderStatus status,
            @Param("username") String username
    );

    int save(@Param("order") Order order);

    OrderResponse findByIdAndUsername(@Param("orderId") Long orderId, @Param("username") String username);

    int countAndDeletedAtIsNull(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("status") OrderStatus status,
            @Param("username") String username
    );

    int updateStatus(@Param("orderId") Long orderId, @Param("username") String username);
}
