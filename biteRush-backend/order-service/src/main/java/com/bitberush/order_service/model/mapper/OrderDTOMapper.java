package com.bitberush.order_service.model.mapper;

import com.bitberush.order_service.model.dto.request.OrderRequestDTO;
import com.bitberush.order_service.model.dto.response.OrderResponseDTO;
import com.bitberush.order_service.model.entity.Order;

public class OrderDTOMapper {

    public static OrderResponseDTO toDTO(Order order) {
        return OrderResponseDTO.builder()
                .orderId(order.getOrderId())
                .orderNumber(order.getOrderNumber())
                .userId(order.getUserId())
                .restaurantId(order.getRestaurantId())
                .deliveryAddressId(order.getDeliveryAddressId())
                .status(order.getStatus())
                .paymentStatus(order.getPaymentStatus())
                .subtotal(order.getSubtotal())
                .deliveryFee(order.getDeliveryFee())
                .discount(order.getDiscount())
                .total(order.getTotal())
                .estimatedDeliveryTime(order.getEstimatedDeliveryTime())
                .deliveredAt(order.getDeliveredAt())
                .cancelledAt(order.getCancelledAt())
                .cancelReason(order.getCancelReason())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .orderItems(order.getOrderItems().stream().map(OrderItemDTOMapper::toDTO).toList())
                .build();
    }

    public static Order toEntity(OrderRequestDTO requestDTO) {
        return Order.builder()
                .userId(requestDTO.getUserId())
                .restaurantId(requestDTO.getRestaurantId())
                .deliveryAddressId(requestDTO.getDeliveryAddressId())
                .status(requestDTO.getStatus())
                .paymentStatus(requestDTO.getPaymentStatus())
                .subtotal(requestDTO.getSubtotal())
                .deliveryFee(requestDTO.getDeliveryFee())
                .discount(requestDTO.getDiscount())
                .build();
    }

}
