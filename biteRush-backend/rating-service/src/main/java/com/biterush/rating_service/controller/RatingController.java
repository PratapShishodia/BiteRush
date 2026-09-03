package com.biterush.rating_service.controller;

import com.biterush.rating_service.model.dto.request.RatingRequestDTO;
import com.biterush.rating_service.model.dto.response.RatingResponseDTO;
import com.biterush.rating_service.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<String> createReview(RatingRequestDTO ratingRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createReview(ratingRequestDTO));
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<String> updateReview(@PathVariable String ratingId, @RequestParam String review, @RequestParam BigDecimal ratingNum) {
        return ResponseEntity.ok(ratingService.updateReview(ratingId,review,ratingNum));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteReview(@PathVariable String ratingId) {
        return ResponseEntity.ok(ratingService.deleteReview(ratingId));
    }

    @GetMapping("/{userId}/users")
    public ResponseEntity<List<RatingResponseDTO>> getUserRatings(@PathVariable UUID userId) {
        return ResponseEntity.ok(ratingService.getReviewsByUsernameId(userId));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<RatingResponseDTO>> getRestaurantRatings(@PathVariable UUID restaurantId) {
        return ResponseEntity.ok(ratingService.getReviewsByRestaurantId(restaurantId));
    }

    @GetMapping("/{restaurantId}/count")
    public ResponseEntity<Integer> ratingCount(@PathVariable UUID restaurantId) {
        return ResponseEntity.ok(ratingService.countByRestaurantId(restaurantId));
    }
}
