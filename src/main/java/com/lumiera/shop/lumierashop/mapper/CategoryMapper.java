package com.lumiera.shop.lumierashop.mapper;

import com.lumiera.shop.lumierashop.domain.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> findAll();
}
