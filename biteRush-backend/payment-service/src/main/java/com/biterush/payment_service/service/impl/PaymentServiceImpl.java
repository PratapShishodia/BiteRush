package com.biterush.payment_service.service.impl;

import com.biterush.payment_service.model.dto.request.PaymentRequestDTO;
import com.biterush.payment_service.model.entity.Payment;
import com.biterush.payment_service.model.enums.STATUS;
import com.biterush.payment_service.model.mapper.PaymentDTOMapper;
import com.biterush.payment_service.repository.PaymentRepo;
import com.biterush.payment_service.service.PaymentService;
import com.biterush.payment_service.utils.PaymentFunction;
import com.biterush.payment_service.utils.PaymentMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;
    private final PaymentFunction paymentFunction;

    @Override
    public String makePayment(PaymentRequestDTO requestDTO) {
        String returnValue = "";
        Payment payment = PaymentDTOMapper.toEntity(requestDTO);
        UUID transactionId = UUID.randomUUID();
        payment.setProvider("DEFAULT");
        payment.setTransactionId(transactionId);
        PaymentMethods paymentMethods = paymentFunction.paymentFunction(requestDTO.getMethod());
        if(paymentMethods.pay(transactionId,requestDTO.getAmount())){
            payment.setStatus(STATUS.SUCCESS);
            returnValue = "Payment Successful";
        }
        else{
            returnValue = "Payment Failed";
            payment.setStatus(STATUS.FAILED);
        }
        paymentRepo.save(payment);
        return returnValue;
    }

    @Override
    public String updatePaymentStatus(UUID paymentId, STATUS status) {
        Payment payment = paymentRepo.findById(paymentId).orElseThrow(()->new RuntimeException("Payment Not Found"));
        payment.setStatus(status);
        paymentRepo.save(payment);
        return "Status Updated Successfully";
    }

    @Override
    public String refundPayment(UUID paymentId) {
        Payment payment = paymentRepo.findById(paymentId).orElseThrow(()->new RuntimeException("Payment Not Found"));
        payment.setTransactionId(UUID.randomUUID());
        payment.setStatus(STATUS.REFUNDED);
        paymentRepo.save(payment);
        return "Refund Successful";
    }

    @Override
    public Payment findByPaymentId(UUID paymentId) {
        return paymentRepo.findById(paymentId).orElseThrow(()->new RuntimeException("Payment Not Found"));
    }

    @Override
    public Payment findByTransactionId(UUID transactionId) {
        return paymentRepo.findByTransactionId(transactionId);
    }

    @Override
    public List<Payment> getAllByUserId(UUID userId) {
        return paymentRepo.findByUserId(userId);
    }
}
