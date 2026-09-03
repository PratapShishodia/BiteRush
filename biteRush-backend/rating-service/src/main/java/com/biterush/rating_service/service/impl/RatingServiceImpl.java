package com.biterush.rating_service.service.impl;

import com.biterush.rating_service.model.dto.request.RatingRequestDTO;
import com.biterush.rating_service.model.dto.response.RatingResponseDTO;
import com.biterush.rating_service.model.entity.Rating;
import com.biterush.rating_service.model.mapper.RatingDTOMapper;
import com.biterush.rating_service.repository.RatingRepo;
import com.biterush.rating_service.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RatingServiceImpl implements RatingService {

    private final RatingRepo ratingRepo;

//    @Override
//    public RatingResponseDTO getReviewById(String reviewId) {
//        Rating rating = ratingRepo.findById(reviewId).orElseThrow(()-> new RuntimeException("Review not Found"));
//        return RatingDTOMapper.toDTO(rating);
//    }

    @Override
    @Transactional
    public String createReview(RatingRequestDTO ratingRequestDTO) {
        Rating rating = RatingDTOMapper.toEntity(ratingRequestDTO);
        rating.setCreatedAt(LocalDateTime.now());
        return "Review Added Successfully";
    }

    @Override
    @Transactional
    public String updateReview(String ratingId, String review, BigDecimal ratingNum) {
        Rating rating = ratingRepo.findById(ratingId).orElseThrow(()-> new RuntimeException("Review not Found"));
        rating.setRating(ratingNum);
        rating.setReviewText(review);
        rating.setUpdatedAt(LocalDateTime.now());
        ratingRepo.save(rating);
        return "Review Updated Successfully";
    }

    @Override
    @Transactional
    public String deleteReview(String reviewId) {
        Rating rating = ratingRepo.findById(reviewId).orElseThrow(()-> new RuntimeException("Review not Found"));
        ratingRepo.delete(rating);
        return "Rating Deleted Successfully";
    }

    @Override
    public List<RatingResponseDTO> getReviewsByUsernameId(UUID userId) {
        List<Rating> ratings = ratingRepo.findByUserId(userId);
        return ratings.stream().map(RatingDTOMapper::toDTO).toList();
    }

    @Override
    public List<RatingResponseDTO> getReviewsByRestaurantId(UUID restaurantId) {
        List<Rating> ratings = ratingRepo.findByRestaurantId(restaurantId);
        return ratings.stream().map(RatingDTOMapper::toDTO).toList();
    }

    @Override
    public Integer countByRestaurantId(UUID restaurantId) {
        return ratingRepo.countByRestaurantId(restaurantId);
    }
}
