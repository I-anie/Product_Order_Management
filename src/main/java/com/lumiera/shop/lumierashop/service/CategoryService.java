package com.lumiera.shop.lumierashop.service;

import com.lumiera.shop.lumierashop.domain.Category;
import com.lumiera.shop.lumierashop.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public List<Category> getCategoryList() {
        return categoryMapper.findAll();
    }
}
