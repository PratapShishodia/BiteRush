package com.biterush.restaurant_service.controller;

import com.biterush.restaurant_service.model.dto.request.CuisineRequestDTO;
import com.biterush.restaurant_service.model.dto.response.CuisineResponseDTO;
import com.biterush.restaurant_service.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cuisine")
public class CuisineController {

    private final RestaurantService restaurantService;

    @GetMapping()
    public ResponseEntity<List<CuisineResponseDTO>> getAllCuisines(){
        return ResponseEntity.ok(restaurantService.getAllCuisine());
    }

    @PostMapping()
    public ResponseEntity<CuisineResponseDTO> createCuisine(@RequestBody CuisineRequestDTO cuisineRequestDTO){
        return ResponseEntity.ok(restaurantService.createCuisine(cuisineRequestDTO));
    }

    @GetMapping("/{cuisineId}")
    public ResponseEntity<CuisineResponseDTO> getCuisine(@PathVariable Long cuisineId){
        return ResponseEntity.ok(restaurantService.getCuisine(cuisineId));
    }
}
