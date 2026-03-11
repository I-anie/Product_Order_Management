package com.lumiera.shop.lumierashop.controller;

import com.lumiera.shop.lumierashop.dto.request.CartItemForm;
import com.lumiera.shop.lumierashop.global.security.CustomUserDetails;
import com.lumiera.shop.lumierashop.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class CartController {

    private static final String CART_VIEW = "cart/cart";
    private static final String REDIRECT_CART = "redirect:/cart";
    private static final String REDIRECT_PRODUCT = "redirect:/products/";

    private final CartService cartService;

    @GetMapping
    public String getCart(
            Model model,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        model.addAttribute("cartItems", cartService.getItems(userDetails.getUsername()));
        return CART_VIEW;
    }

    @PostMapping("/add")
    public String addItem(
            @Valid @ModelAttribute("cartItem") CartItemForm cartItemForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        if (bindingResult.hasErrors()) {
            return redirectToProductDetailWithValidationError(cartItemForm, bindingResult, redirectAttributes);
        }

        cartService.addItem(cartItemForm, userDetails.getUsername());
        return REDIRECT_CART;
    }

    @PostMapping("/{itemId}/update")
    public String updateItem(
            @PathVariable Long itemId,
            @Valid @ModelAttribute("cartItem") CartItemForm cartItemForm,
            BindingResult bindingResult,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        if (bindingResult.hasErrors()) {
            return REDIRECT_CART;
        }

        cartService.updateItem(itemId, cartItemForm, userDetails.getUsername());
        return REDIRECT_CART;
    }

    @PostMapping("/{itemId}/delete")
    public String deleteItem(
            @PathVariable Long itemId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        cartService.deleteItem(itemId, userDetails.getUsername());
        return REDIRECT_CART;
    }

    private String redirectToProductDetailWithValidationError(
            CartItemForm cartItemForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("cartItem", cartItemForm);
        redirectAttributes.addFlashAttribute(
                "org.springframework.validation.BindingResult.cartItem",
                bindingResult
        );

        return REDIRECT_PRODUCT + cartItemForm.getProductId();
    }
}