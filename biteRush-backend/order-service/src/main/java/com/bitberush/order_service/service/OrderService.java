package com.bitberush.order_service.service;

import com.bitberush.order_service.model.dto.request.OrderRequestDTO;
import com.bitberush.order_service.model.dto.response.OrderResponseDTO;
import com.bitberush.order_service.model.entity.Order;
import com.bitberush.order_service.model.entity.OrderStatusHistory;
import com.bitberush.order_service.model.enums.STATUS;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO);
    String cancelOrder(UUID orderId,String cancelReason);
    String updateOrderStatus(UUID orderId, STATUS status);
    List<Order> getAllOrders(UUID userId);
    Order getOrderByOrderId(UUID orderId);
    List<OrderStatusHistory> getStatusHistoryByOrderId(UUID orderId);
}
