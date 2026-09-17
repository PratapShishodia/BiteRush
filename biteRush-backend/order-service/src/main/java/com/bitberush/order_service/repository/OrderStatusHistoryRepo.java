package com.bitberush.order_service.repository;

import com.bitberush.order_service.model.entity.OrderStatusHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderStatusHistoryRepo extends MongoRepository<OrderStatusHistory, UUID> {
    List<OrderStatusHistory> findByOrderId(UUID orderId);
}
