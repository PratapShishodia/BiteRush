package com.biterush.restaurant_service.controller;

import com.biterush.restaurant_service.model.dto.request.CuisineRequestDTO;
import com.biterush.restaurant_service.model.dto.request.RestaurantHoursRequestDTO;
import com.biterush.restaurant_service.model.dto.request.RestaurantRequestDTO;
import com.biterush.restaurant_service.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RestaurantRestController {

    private final RestaurantService restaurantService;

//    @PostMapping("/restaurants")
//    public ResponseEntity<String> createRestaurant(@RequestBody RestaurantRequestDTO restaurantRequestDTO) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.createRestaurant(restaurantRequestDTO));
//    }

//    For Development Purpose Only
    @PostMapping("/restaurants")
    public ResponseEntity<String> createRestaurant(@RequestBody List<RestaurantRequestDTO> restaurantRequestDTO) {
        for(RestaurantRequestDTO dto : restaurantRequestDTO) {
            restaurantService.createRestaurant(dto);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Data added successfully");
    }

    @PutMapping("/restaurants/{restaurantId}")
    public ResponseEntity<String> updateRestaurant(@PathVariable UUID restaurantId, @RequestBody RestaurantRequestDTO restaurantRequestDTO) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantId, restaurantRequestDTO));
    }

    @PatchMapping("/restaurants/{restaurantId}/activate")
    public ResponseEntity<String> activateRestaurant(@PathVariable UUID restaurantId) {
        return ResponseEntity.ok(restaurantService.activateRestaurant(restaurantId));
    }

    @PatchMapping("/restaurants/{restaurantId}/deactivate")
    public ResponseEntity<String> deactivateRestaurant(@PathVariable UUID restaurantId) {
        return ResponseEntity.ok(restaurantService.deactivateRestaurant(restaurantId));
    }

    @PutMapping("/restaurantHours/{restaurantId}")
    public ResponseEntity<String> updateRestaurantHours(@PathVariable UUID restaurantId, @RequestBody RestaurantHoursRequestDTO requestDTO) {
        return ResponseEntity.ok(restaurantService.updateRestaurantHours(restaurantId, requestDTO));
    }

//    @PostMapping("/restaurantHours")
//    public ResponseEntity<String> createRestaurantHours(@RequestBody RestaurantHoursRequestDTO requestDTO) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.createRestaurantHours(requestDTO));
//    }

//    For Development Purpose Only
    @PostMapping("/restaurantHours")
    public ResponseEntity<String> createRestaurantHours(@RequestBody List<RestaurantHoursRequestDTO> requestDTO) {
        for(RestaurantHoursRequestDTO dto : requestDTO) {
            restaurantService.createRestaurantHours(dto);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Data added successfully");
    }

//    @PostMapping("/cuisines")
//    public ResponseEntity<String> createCuisine(@RequestBody CuisineRequestDTO cuisineRequestDTO) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.createCuisine(cuisineRequestDTO));
//    }

//    For development Purpose Only
    @PostMapping("/cuisines")
    public ResponseEntity<String> createCuisine(@RequestBody List<CuisineRequestDTO> cuisineRequestDTO) {
        for(CuisineRequestDTO dto : cuisineRequestDTO) {
            restaurantService.createCuisine(dto);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Data added successfully");
    }

    @PatchMapping("/restaurants/{restaurantId}/logo")
    public ResponseEntity<String> uploadRestaurantLogo(@PathVariable UUID restaurantId, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(restaurantService.uploadRestaurantLogo(restaurantId, file));
    }

    @PatchMapping("/restaurants/{restaurantId}/banner")
    public ResponseEntity<String> uploadRestaurantBanner(@PathVariable UUID restaurantId, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(restaurantService.uploadRestaurantBanner(restaurantId, file));
    }

    @PatchMapping("/cuisines/{cuisineId}/image")
    public ResponseEntity<String> uploadCuisineImage(@PathVariable UUID cuisineId, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(restaurantService.uploadCuisineImage(cuisineId, file));
    }

}
