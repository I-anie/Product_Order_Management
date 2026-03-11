package com.lumiera.shop.lumierashop.mapper;

import com.lumiera.shop.lumierashop.domain.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    int save(@Param("orderItems") List<OrderItem> orderItems);
}
