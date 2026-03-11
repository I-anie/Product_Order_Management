package com.lumiera.shop.lumierashop.controller;

import com.lumiera.shop.lumierashop.dto.request.CreateProductForm;
import com.lumiera.shop.lumierashop.dto.request.DeleteProductForm;
import com.lumiera.shop.lumierashop.dto.request.ProductSearchCondition;
import com.lumiera.shop.lumierashop.dto.request.UpdateProductForm;
import com.lumiera.shop.lumierashop.dto.response.ProductListResponse;
import com.lumiera.shop.lumierashop.dto.response.ProductResponse;
import com.lumiera.shop.lumierashop.global.common.pagination.PageHandler;
import com.lumiera.shop.lumierashop.global.common.pagination.PaginationService;
import com.lumiera.shop.lumierashop.service.AdminProductService;
import com.lumiera.shop.lumierashop.service.CategoryService;
import com.lumiera.shop.lumierashop.service.ProductDetailImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminProductController {

    private static final String PRODUCT_LIST_VIEW = "product/productList";
    private static final String CREATE_PRODUCT_VIEW = "product/createProduct";
    private static final String UPDATE_PRODUCT_VIEW = "product/updateProduct";
    private static final String PRODUCT_VIEW = "product/product";
    private static final String REDIRECT_PRODUCT_LIST = "redirect:/admin/products";
    private static final String REDIRECT_PRODUCT_DETAIL = "redirect:/admin/products/";

    private final AdminProductService productService;
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
                searchCondition.isDeleted(),
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
                searchCondition.isDeleted(),
                searchCondition.getCategoryId()
        );

        model.addAttribute("productList", productList);
        model.addAttribute("pageHandler", pageHandler);
        model.addAttribute("searchCondition", searchCondition);
        model.addAttribute("categoryList", categoryService.getCategoryList());

        return PRODUCT_LIST_VIEW;
    }

    @GetMapping("/create")
    public String createProductForm(@ModelAttribute("createForm") CreateProductForm createForm, Model model) {
        model.addAttribute("categoryList", categoryService.getCategoryList());
        return CREATE_PRODUCT_VIEW;
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute("createForm") CreateProductForm createForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryList", categoryService.getCategoryList());
            return CREATE_PRODUCT_VIEW;
        }

        Long productId = productService.createProduct(createForm);
        return REDIRECT_PRODUCT_DETAIL + productId;
    }

    @GetMapping("/{productId}")
    public String getProduct(@PathVariable Long productId, Model model) {
        model.addAttribute("product", productService.getProduct(productId));
        model.addAttribute("detailImages", productDetailImageService.getDetailImages(productId));
        return PRODUCT_VIEW;
    }

    @GetMapping("/{productId}/update")
    public String updateProductForm(@PathVariable Long productId, Model model) {
        ProductResponse product = productService.getProduct(productId);

        UpdateProductForm updateForm = new UpdateProductForm(
                product.getCategoryId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity()
        );

        addUpdateFormAttributes(model, productId, updateForm);
        return UPDATE_PRODUCT_VIEW;
    }

    @PostMapping("/{productId}/update")
    public String updateProduct(
            @PathVariable Long productId,
            @Valid @ModelAttribute("updateForm") UpdateProductForm updateForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            addUpdateFormAttributes(model, productId, updateForm);
            return UPDATE_PRODUCT_VIEW;
        }

        productService.updateProduct(productId, updateForm);
        return REDIRECT_PRODUCT_DETAIL + productId;
    }

    @PostMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return REDIRECT_PRODUCT_LIST;
    }

    @PostMapping("/delete")
    public String deleteProducts(@Valid DeleteProductForm deleteForm, RedirectAttributes redirectAttributes) {
        int requestCount = deleteForm.getProductIds().stream().distinct().toList().size();
        int deletedCount = productService.deleteProducts(deleteForm.getProductIds());

        if (deletedCount == 0) {
            redirectAttributes.addFlashAttribute("message", "삭제된 상품이 없습니다.");
        } else if (deletedCount < requestCount) {
            redirectAttributes.addFlashAttribute(
                    "message",
                    String.format("%d건 중 %d건이 삭제되었습니다.", requestCount, deletedCount)
            );
        } else {
            redirectAttributes.addFlashAttribute(
                    "message",
                    String.format("%d건의 상품이 삭제되었습니다.", deletedCount)
            );
        }

        return REDIRECT_PRODUCT_LIST;
    }

    private void addUpdateFormAttributes(Model model, Long productId, UpdateProductForm updateForm) {
        model.addAttribute("productId", productId);
        model.addAttribute("updateForm", updateForm);
        model.addAttribute("categoryList", categoryService.getCategoryList());
        model.addAttribute("detailImages", productDetailImageService.getDetailImages(productId));
    }
}