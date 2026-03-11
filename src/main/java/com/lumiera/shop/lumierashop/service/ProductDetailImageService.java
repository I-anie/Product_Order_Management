package com.lumiera.shop.lumierashop.service;

import com.lumiera.shop.lumierashop.domain.ProductDetailImage;
import com.lumiera.shop.lumierashop.mapper.ProductDetailImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductDetailImageService {

    private final ProductDetailImageMapper productDetailImageMapper;

    @Transactional
    public void saveDetailImages(Long productId, List<String> imageUrls) {
        if (imageUrls == null || imageUrls.isEmpty()) {
            return;
        }

        List<ProductDetailImage> detailImages = new ArrayList<>();

        for (int i = 0; i < imageUrls.size(); i++) {
            detailImages.add(new ProductDetailImage(productId, imageUrls.get(i), i + 1));
        }

        productDetailImageMapper.save(detailImages);
    }

    public List<ProductDetailImage> getDetailImages(Long productId) {
        return productDetailImageMapper.findByProductId(productId);
    }

    @Transactional
    public void deleteDetailImages(Long productId) {
        deleteDetailImages(List.of(productId));
    }

    @Transactional
    public void deleteDetailImages(List<Long> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return;
        }

        productDetailImageMapper.deleteByProductId(productIds);
    }
}