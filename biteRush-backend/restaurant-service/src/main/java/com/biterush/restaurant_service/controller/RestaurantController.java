package com.biterush.restaurant_service.controller;

import com.biterush.restaurant_service.model.dto.request.RestaurantHoursRequestDTO;
import com.biterush.restaurant_service.model.dto.request.RestaurantRequestDTO;
import com.biterush.restaurant_service.model.dto.response.RestaurantHoursResponseDTO;
import com.biterush.restaurant_service.model.dto.response.RestaurantResponseDTO;
import com.biterush.restaurant_service.service.RestaurantService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping()
    public ResponseEntity<RestaurantResponseDTO> createRestaurant(@RequestBody RestaurantRequestDTO restaurantRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.createRestaurant(restaurantRequestDTO));
    }

    @GetMapping()
    public ResponseEntity<List<RestaurantResponseDTO>> getAllRestaurants(){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurant(@PathVariable UUID restaurantId){
        return ResponseEntity.ok(restaurantService.getRestaurants(restaurantId));
    }
    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(@PathVariable UUID restaurantId, @RequestBody RestaurantRequestDTO restaurantRequestDTO){
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantId, restaurantRequestDTO));
    }

    @PatchMapping("/{restaurantId}/activate")
    public ResponseEntity<String> activateRestaurant(@PathVariable UUID restaurantId){
        return ResponseEntity.ok(restaurantService.activateRestaurant(restaurantId));
    }

    @PatchMapping("/{restaurantId}/deactivate")
    public ResponseEntity<String> deactivateRestaurant(@PathVariable UUID restaurantId){
        return ResponseEntity.ok(restaurantService.deactivateRestaurant(restaurantId));
    }

    @GetMapping("/{restaurantId}/hours")
    public ResponseEntity<RestaurantHoursResponseDTO> getRestaurantHours(@PathVariable UUID restaurantId){
        return ResponseEntity.ok(restaurantService.getRestaurantHours(restaurantId));
    }

    @PutMapping("/{restaurantId}/hours")
    public ResponseEntity<RestaurantHoursResponseDTO> updateRestaurantHours(@PathVariable UUID restaurantId, @RequestBody RestaurantHoursRequestDTO restaurantHoursRequestDTO){
        return ResponseEntity.ok(restaurantService.updateRestaurantHours(restaurantId, restaurantHoursRequestDTO));
    }
}
