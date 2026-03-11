package com.lumiera.shop.lumierashop.controller;

import com.lumiera.shop.lumierashop.domain.Order;
import com.lumiera.shop.lumierashop.domain.enums.OrderStatus;
import com.lumiera.shop.lumierashop.dto.request.OrderSearchCondition;
import com.lumiera.shop.lumierashop.dto.request.UpdateStatusForm;
import com.lumiera.shop.lumierashop.global.common.pagination.PageHandler;
import com.lumiera.shop.lumierashop.global.common.pagination.PaginationService;
import com.lumiera.shop.lumierashop.service.AdminOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminOrderController {

    private static final String ORDER_LIST_VIEW = "order/orderList";
    private static final String ORDER_VIEW = "order/order";
    private static final String REDIRECT_ORDER_LIST = "redirect:/admin/orders";

    private final AdminOrderService orderService;
    private final PaginationService paginationService;

    @GetMapping
    public String getOrderList(
            @ModelAttribute("searchCondition") OrderSearchCondition searchCondition,
            Model model
    ) {
        boolean deleted = searchCondition.isDeleted();
        LocalDateTime startDateTime = searchCondition.getStartDateTimeOrNull();
        LocalDateTime endDateTime = searchCondition.getEndDateTimeOrNull();
        OrderStatus status = searchCondition.getStatus();
        String username = searchCondition.getUsername();

        int totalCount = orderService.getOrderCount(
                startDateTime,
                endDateTime,
                status,
                username,
                deleted
        );

        PageHandler pageHandler = paginationService.of(
                searchCondition.getPageOrDefault(),
                searchCondition.getSizeOrDefault(),
                totalCount
        );

        List<Order> orderList = orderService.getOrderList(
                pageHandler.getOffset(),
                pageHandler.getLimit(),
                startDateTime,
                endDateTime,
                status,
                username,
                deleted
        );

        model.addAttribute("orderList", orderList);
        model.addAttribute("pageHandler", pageHandler);
        model.addAttribute("searchCondition", searchCondition);

        return ORDER_LIST_VIEW;
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.getOrder(orderId));
        return ORDER_VIEW;
    }

    @PostMapping("/preparing")
    public String changeToPreparing(
            @Valid @ModelAttribute UpdateStatusForm updateForm,
            RedirectAttributes redirectAttributes
    ) {
        int requestCount = updateForm.getOrderIds().size();
        int updatedCount = orderService.changeToPreparing(updateForm.getOrderIds());

        if (updatedCount == 0) {
            redirectAttributes.addFlashAttribute("message", "변경된 주문이 없습니다.");
        } else if (updatedCount < requestCount) {
            redirectAttributes.addFlashAttribute(
                    "message",
                    String.format("%d건 중 %d건이 변경되었습니다.", requestCount, updatedCount)
            );
        } else {
            redirectAttributes.addFlashAttribute(
                    "message",
                    String.format("%d건의 상품이 변경되었습니다.", updatedCount)
            );
        }

        return REDIRECT_ORDER_LIST;
    }
}