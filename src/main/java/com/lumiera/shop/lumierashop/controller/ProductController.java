package com.lumiera.shop.lumierashop.controller;

import com.lumiera.shop.lumierashop.domain.CartItem;
import com.lumiera.shop.lumierashop.dto.request.ProductSearchCondition;
import com.lumiera.shop.lumierashop.dto.response.ProductListResponse;
import com.lumiera.shop.lumierashop.global.common.pagination.PageHandler;
import com.lumiera.shop.lumierashop.global.common.pagination.PaginationService;
import com.lumiera.shop.lumierashop.service.CategoryService;
import com.lumiera.shop.lumierashop.service.ProductDetailImageService;
import com.lumiera.shop.lumierashop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private static final String PRODUCT_LIST_VIEW = "product/productList";
    private static final String PRODUCT_VIEW = "product/product";

    private final ProductService productService;
    private final ProductDetailImageService productDetailImageService;
    private final CategoryService categoryService;
    private final PaginationService paginationService;

    @GetMapping
    public String getProductList(
            @ModelAttribute("searchCondition") ProductSearchCondition searchCondition,
            Model model
    ) {
        int totalCount = productService.getTotalCount(
                searchCondition.getKeyword(),
                searchCondition.getCategoryId()
        );

        PageHandler pageHandler = paginationService.of(
                searchCondition.getPageOrDefault(),
                searchCondition.getSizeOrDefault(),
                totalCount
        );

        List<ProductListResponse> productList = productService.getProductList(
                pageHandler.getOffset(),
                pageHandler.getLimit(),
                searchCondition.getKeyword(),
                searchCondition.getCategoryId()
        );

        model.addAttribute("productList", productList);
        model.addAttribute("pageHandler", pageHandler);
        model.addAttribute("searchCondition", searchCondition);
        model.addAttribute("categoryList", categoryService.getCategoryList());

        return PRODUCT_LIST_VIEW;
    }

    @GetMapping("/{productId}")
    public String getProduct(@PathVariable Long productId, Model model) {
        model.addAttribute("product", productService.getProduct(productId));
        model.addAttribute("detailImages", productDetailImageService.getDetailImages(productId));

        if (!model.containsAttribute("cartItem")) {
            model.addAttribute("cartItem", new CartItem(productId));
        }

        return PRODUCT_VIEW;
    }
}