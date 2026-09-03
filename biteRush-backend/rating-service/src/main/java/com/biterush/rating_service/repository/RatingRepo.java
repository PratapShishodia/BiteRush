package com.biterush.rating_service.repository;

import com.biterush.rating_service.model.dto.response.RatingResponseDTO;
import com.biterush.rating_service.model.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepo extends MongoRepository<Rating,String> {
    List<Rating> findByUserId(UUID userId);
    List<Rating> findByRestaurantId(UUID restaurantId);
    Integer countByRestaurantId(UUID restaurantId);
}
