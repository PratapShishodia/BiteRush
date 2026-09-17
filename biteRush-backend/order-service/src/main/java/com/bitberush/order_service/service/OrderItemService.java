package com.bitberush.order_service.service;

import com.bitberush.order_service.model.dto.request.OrderItemRequestDTO;
import com.bitberush.order_service.model.dto.response.OrderItemResponseDTO;

import java.util.UUID;

public interface OrderItemService {
    OrderItemResponseDTO addOrderItem(OrderItemRequestDTO orderItemRequestDTO);
}
