package com.biterush.restaurant_service.service;

import com.biterush.restaurant_service.model.dto.request.CuisineRequestDTO;
import com.biterush.restaurant_service.model.dto.request.RestaurantHoursRequestDTO;
import com.biterush.restaurant_service.model.dto.request.RestaurantRequestDTO;
import com.biterush.restaurant_service.model.entity.Cuisine;
import com.biterush.restaurant_service.model.entity.Restaurant;
import com.biterush.restaurant_service.model.entity.RestaurantHours;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {
    String createRestaurant(RestaurantRequestDTO restaurantRequestDTO);
    List<Restaurant> getAllRestaurants();
    String uploadRestaurantLogo(UUID restaurantId, MultipartFile file);
    String uploadRestaurantBanner(UUID restaurantId, MultipartFile file);
    String uploadCuisineImage(UUID cuisineId, MultipartFile file);
    Restaurant getRestaurants(UUID restaurantId);
    String updateRestaurant(UUID restaurantId,RestaurantRequestDTO restaurantRequestDTO);
    String activateRestaurant(UUID restaurantId);
    String deactivateRestaurant(UUID restaurantId);
    String createRestaurantHours(RestaurantHoursRequestDTO requestDTO);
    RestaurantHours getRestaurantHours(UUID restaurantId);
    String updateRestaurantHours(UUID restaurantId, RestaurantHoursRequestDTO requestDTO);
    List<Cuisine> getAllCuisine();
    String createCuisine(CuisineRequestDTO cuisineRequestDTO);
    Cuisine getCuisine(UUID cuisineId);
    List<Cuisine> getCuisinesByRestaurantId(UUID restaurantId);
    List<Restaurant> getRestaurantsByCuisineId(UUID cuisineId);
}
