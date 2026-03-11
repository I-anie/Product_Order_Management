package com.lumiera.shop.lumierashop.mapper;

import com.lumiera.shop.lumierashop.domain.Product;
import com.lumiera.shop.lumierashop.dto.response.ProductListResponse;
import com.lumiera.shop.lumierashop.dto.response.ProductResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import java.util.List;

public interface AdminProductMapper {

    List<ProductListResponse> findAll(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("keyword") String keyword,
            @Param("deleted") boolean deleted,
            @Param("categoryId") Long categoryId
    );

    int save(@Param("product") Product product);

    ProductResponse findById(@Param("productId") Long productId);

    int update(@Param("productId") Long productId, @Param("product") Product product);

    int delete(@Param("productIds") List<Long> productIds);

    int count(
            @Param("keyword") String keyword,
            @Param("deleted") boolean deleted,
            @Param("categoryId") Long categoryId
    );
}
