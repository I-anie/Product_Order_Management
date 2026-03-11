package com.lumiera.shop.lumierashop.mapper;

import com.lumiera.shop.lumierashop.domain.CartItem;
import com.lumiera.shop.lumierashop.dto.response.CartResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    List<CartResponse> findByUsername(@Param("username") String username);

    int save(@Param("cartItem") CartItem cartItem);

    int update(
            @Param("itemId") Long itemId,
            @Param("quantity") int quantity,
            @Param("username") String username
    );

    int delete(@Param("itemId") Long itemId, @Param("username") String username);

    List<CartResponse> findCartItemsByIds(
            @Param("cartItemIds") List<Long> cartItemIds,
            @Param("username") String username);
}
