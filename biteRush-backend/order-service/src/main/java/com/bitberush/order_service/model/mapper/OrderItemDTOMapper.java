package com.bitberush.order_service.model.mapper;

import com.bitberush.order_service.model.dto.request.OrderItemRequestDTO;
import com.bitberush.order_service.model.dto.response.OrderItemResponseDTO;
import com.bitberush.order_service.model.entity.OrderItem;

public class OrderItemDTOMapper {
    public static OrderItemResponseDTO toDTO(OrderItem orderItem) {
        return OrderItemResponseDTO.builder()
                .orderItemId(orderItem.getOrderItemId())
                .menuItemId(orderItem.getMenuItemId())
                .itemName(orderItem.getItemName())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .totalPrice(orderItem.getTotalPrice())
                .build();
    }

    public static OrderItem toEntity(OrderItemRequestDTO requestDTO) {
        return OrderItem.builder()
                .menuItemId(requestDTO.getMenuItemId())
                .build();
    }
}
