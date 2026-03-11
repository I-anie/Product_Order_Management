package com.lumiera.shop.lumierashop.service;

import com.lumiera.shop.lumierashop.domain.OrderItem;
import com.lumiera.shop.lumierashop.dto.response.CartResponse;
import com.lumiera.shop.lumierashop.global.error.exception.CustomException;
import com.lumiera.shop.lumierashop.mapper.CartMapper;
import com.lumiera.shop.lumierashop.mapper.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lumiera.shop.lumierashop.global.error.code.ErrorCode.CART_ITEM_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemMapper orderItemMapper;
    private final CartMapper cartMapper;

    public void createOrderItem(Long orderId, List<Long> cartItemIds, String username) {
        List<CartResponse> cartItems = cartMapper.findCartItemsByIds(cartItemIds, username);

        if (cartItems.isEmpty() || cartItems.size() != cartItemIds.size())
            throw new CustomException(CART_ITEM_NOT_FOUND);

        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> new OrderItem(
                        orderId,
                        cartItem.getPrice(),
                        cartItem.getProductId(),
                        cartItem.getQuantity()
                ))
                .toList();

        orderItemMapper.save(orderItems);
    }
}
