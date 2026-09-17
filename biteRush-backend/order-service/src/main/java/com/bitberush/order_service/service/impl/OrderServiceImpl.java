package com.bitberush.order_service.service.impl;

import com.bitberush.order_service.model.dto.request.OrderItemRequestDTO;
import com.bitberush.order_service.model.dto.request.OrderRequestDTO;
import com.bitberush.order_service.model.dto.response.OrderResponseDTO;
import com.bitberush.order_service.model.entity.Order;
import com.bitberush.order_service.model.entity.OrderStatusHistory;
import com.bitberush.order_service.model.enums.STATUS;
import com.bitberush.order_service.model.mapper.OrderDTOMapper;
import com.bitberush.order_service.repository.OrderItemRepo;
import com.bitberush.order_service.repository.OrderRepo;
import com.bitberush.order_service.repository.OrderStatusHistoryRepo;
import com.bitberush.order_service.service.OrderItemService;
import com.bitberush.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderStatusHistoryRepo orderStatusHistoryRepo;
    private final OrderItemService orderItemService;

    @Override
    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) {
        Order order = OrderDTOMapper.toEntity(orderRequestDTO);
        Order savedOrder = orderRepo.save(order);
        for(UUID menuItemId : orderRequestDTO.getMenuItemIds()) {
            orderItemService.addOrderItem(OrderItemRequestDTO.builder().menuItemId(menuItemId).orderId(savedOrder.getOrderId()).build());
        }
        orderStatusHistoryRepo.save(OrderStatusHistory.builder()
                .orderId(savedOrder.getOrderId())
                .status(savedOrder.getStatus())
                .build());
        return OrderDTOMapper.toDTO(savedOrder);
    }

    @Override
    public String cancelOrder(UUID orderId, String cancelReason) {
        Order order = orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Order not found"));
        order.setStatus(STATUS.CANCELLED);
        order.setCancelReason(cancelReason);
        orderRepo.save(order);
        orderStatusHistoryRepo.save(OrderStatusHistory.builder()
                        .orderId(orderId)
                        .status(STATUS.CANCELLED)
                .build());
        return "Order Cancelled";
    }

    @Override
    public String updateOrderStatus(UUID orderId, STATUS status) {
        Order order = orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepo.save(order);
        orderStatusHistoryRepo.save(OrderStatusHistory.builder()
                        .orderId(orderId)
                .status(status)
                .build());
        return "Order Status Updated";
    }

    @Override
    public List<Order> getAllOrders(UUID userId) {
        return orderRepo.findByUserId(userId);
    }

    @Override
    public Order getOrderByOrderId(UUID orderId) {
        return orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Order not found"));
    }

    @Override
    public List<OrderStatusHistory> getStatusHistoryByOrderId(UUID orderId) {
        return orderStatusHistoryRepo.findByOrderId(orderId);
    }
}
