package com.biterush.payment_service.controller;

import com.biterush.payment_service.model.dto.request.PaymentRequestDTO;
import com.biterush.payment_service.model.enums.STATUS;
import com.biterush.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentRestController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> createPayment(PaymentRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.makePayment(requestDTO));
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<String> updatePayment(@PathVariable UUID paymentId, @RequestParam STATUS status) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.updatePaymentStatus(paymentId, status));
    }

    @PutMapping("/{paymentId}/refund")
    public ResponseEntity<String> refundPayment(@PathVariable UUID paymentId) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.refundPayment(paymentId));
    }

}
