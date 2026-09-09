package com.biterush.rating_service.model.mapper;

import com.biterush.rating_service.model.dto.request.RatingRequestDTO;
import com.biterush.rating_service.model.dto.response.RatingResponseDTO;
import com.biterush.rating_service.model.entity.Rating;


public class RatingDTOMapper {

    public static RatingResponseDTO toDTO(Rating review) {
        return RatingResponseDTO.builder()
                .id(review.getId())
                .usernameId(review.getUserId())
                .username(review.getUsername())
                .restaurantId(review.getRestaurantId())
                .rating(review.getRating())
                .reviewText(review.getReviewText())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

    public static Rating toEntity(RatingRequestDTO requestDTO) {
        return Rating.builder()
                .username(requestDTO.getUsername())
                .userId(requestDTO.getUsernameId())
                .restaurantId(requestDTO.getRestaurantId())
                .rating(requestDTO.getRating())
                .reviewText(requestDTO.getReviewText())
                .build();
    }

}
