package com.lumiera.shop.lumierashop.mapper;

import com.lumiera.shop.lumierashop.domain.ProductDetailImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDetailImageMapper {
    void save(@Param("productDetailImages") List<ProductDetailImage> productDetailImages);

    List<ProductDetailImage> findByProductId(@Param("productId") Long productId);

    void deleteByProductId(@Param("productIds") List<Long> productIds);
}
