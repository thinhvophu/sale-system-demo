package com.thinhvo.salesystem.controller;

import com.thinhvo.salesystem.service.order.dto.OrderRequest;
import com.thinhvo.salesystem.service.order.dto.OrderResponse;
import com.thinhvo.salesystem.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/buy")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @RequestMapping("{itemId}")
    public OrderResponse createOrder(@PathVariable long itemId, @Valid @RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(itemId, orderRequest.getQuantity());
    }
}
