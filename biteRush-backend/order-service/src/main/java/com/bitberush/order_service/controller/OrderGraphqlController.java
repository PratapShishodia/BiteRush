package com.bitberush.order_service.controller;

import com.bitberush.order_service.model.entity.Order;
import com.bitberush.order_service.model.entity.OrderStatusHistory;
import com.bitberush.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OrderGraphqlController {

    private final OrderService orderService;

    @QueryMapping
    public List<OrderStatusHistory> getStatusHistoryByOrderId(@Argument UUID orderId) {
        return orderService.getStatusHistoryByOrderId(orderId);
    }

    @QueryMapping
    public List<Order> getAllOrders(@Argument UUID orderId) {
        return orderService.getAllOrders(orderId);
    }

    @QueryMapping
    public Order getOrderById(@Argument UUID orderId) {
        return orderService.getOrderByOrderId(orderId);
    }

}
