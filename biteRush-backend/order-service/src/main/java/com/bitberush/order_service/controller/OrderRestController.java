package com.bitberush.order_service.controller;

import com.bitberush.order_service.model.dto.request.OrderRequestDTO;
import com.bitberush.order_service.model.dto.response.OrderResponseDTO;
import com.bitberush.order_service.model.enums.STATUS;
import com.bitberush.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderRestController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder(OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(orderRequestDTO));
    }

    @PutMapping("/{orderId}/{cancelReason}")
    public ResponseEntity<String> updateOrder(@PathVariable UUID orderId, @PathVariable String cancelReason) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.cancelOrder(orderId,cancelReason));
    }

    @PatchMapping("/{orderId}/{status}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable UUID orderId, @PathVariable STATUS status) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.updateOrderStatus(orderId,status));
    }
}
