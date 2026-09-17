package com.bitberush.order_service.service.impl;

import com.bitberush.order_service.model.dto.request.OrderItemRequestDTO;
import com.bitberush.order_service.model.dto.response.OrderItemResponseDTO;
import com.bitberush.order_service.model.entity.OrderItem;
import com.bitberush.order_service.model.mapper.OrderItemDTOMapper;
import com.bitberush.order_service.repository.OrderItemRepo;
import com.bitberush.order_service.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepo orderItemRepo;

    @Override
    public OrderItemResponseDTO addOrderItem(OrderItemRequestDTO requestDTO) {
        OrderItem orderItem = OrderItemDTOMapper.toEntity(requestDTO);
        UUID menuItemId = orderItem.getMenuItemId();
//        WebClient for getting MenuItem
        orderItem.setOrder(orderItem.getOrder());
        return OrderItemDTOMapper.toDTO(orderItemRepo.save(orderItem));
    }
}
