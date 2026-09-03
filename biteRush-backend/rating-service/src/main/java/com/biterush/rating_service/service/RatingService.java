package com.biterush.rating_service.service;



import com.biterush.rating_service.model.dto.request.RatingRequestDTO;
import com.biterush.rating_service.model.dto.response.RatingResponseDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface RatingService {
//    RatingResponseDTO getReviewById(String reviewId);
    String createReview(RatingRequestDTO ratingRequestDTO);
    String updateReview(String reviewId, String review, BigDecimal ratingNum);
    String deleteReview(String reviewId);
    List<RatingResponseDTO> getReviewsByUsernameId(UUID userId);
    List<RatingResponseDTO> getReviewsByRestaurantId(UUID restaurantId);
    Integer countByRestaurantId(UUID restaurantId);
}
