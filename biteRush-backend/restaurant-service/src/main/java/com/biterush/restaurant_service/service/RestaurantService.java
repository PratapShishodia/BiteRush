package com.biterush.restaurant_service.service;

import com.biterush.restaurant_service.model.dto.request.CuisineRequestDTO;
import com.biterush.restaurant_service.model.dto.request.RestaurantHoursRequestDTO;
import com.biterush.restaurant_service.model.dto.request.RestaurantRequestDTO;
import com.biterush.restaurant_service.model.dto.response.CuisineResponseDTO;
import com.biterush.restaurant_service.model.dto.response.RestaurantHoursResponseDTO;
import com.biterush.restaurant_service.model.dto.response.RestaurantResponseDTO;


import java.util.List;
import java.util.UUID;

public interface RestaurantService {
    RestaurantResponseDTO createRestaurant(RestaurantRequestDTO restaurantRequestDTO);
    List<RestaurantResponseDTO> getAllRestaurants();
    RestaurantResponseDTO getRestaurants(UUID restaurantId);
    RestaurantResponseDTO updateRestaurant(UUID restaurantId,RestaurantRequestDTO restaurantRequestDTO);
    String activateRestaurant(UUID restaurantId);
    String deactivateRestaurant(UUID restaurantId);
    RestaurantHoursResponseDTO getRestaurantHours(UUID restaurantId);
    RestaurantHoursResponseDTO updateRestaurantHours(UUID restaurantId, RestaurantHoursRequestDTO requestDTO);
    List<CuisineResponseDTO> getAllCuisine();
    CuisineResponseDTO createCuisine(CuisineRequestDTO cuisineRequestDTO);
    CuisineResponseDTO getCuisine(Long cuisineId);
}
