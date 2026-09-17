package com.biterush.payment_service.repository;

import com.biterush.payment_service.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, UUID> {
    Payment findByTransactionId(UUID transactionId);
    List<Payment> findByUserId(UUID userId);
}
