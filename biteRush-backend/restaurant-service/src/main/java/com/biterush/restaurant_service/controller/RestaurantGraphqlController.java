package com.biterush.restaurant_service.controller;

import com.biterush.restaurant_service.model.entity.Cuisine;
import com.biterush.restaurant_service.model.entity.Restaurant;
import com.biterush.restaurant_service.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RestaurantGraphqlController {

    private final RestaurantService restaurantService;

    @QueryMapping
    public List<Restaurant> restaurants() {
        return restaurantService.getAllRestaurants();
    }

    @QueryMapping
    public Restaurant restaurant(@Argument UUID id) {
        return restaurantService.getRestaurants(id);
    }

    @QueryMapping
    public List<Cuisine> cuisines() {
        return restaurantService.getAllCuisine();
    }

    @QueryMapping
    public Cuisine cuisine(@Argument UUID id) {
        return restaurantService.getCuisine(id);
    }

    @SchemaMapping(typeName = "Restaurant", field = "cuisines")
    public List<Cuisine> cuisines(Restaurant restaurant) {

        return restaurantService.getCuisinesByRestaurantId(
                restaurant.getRestaurantId()
        );
    }

    @SchemaMapping(typeName = "Cuisine", field = "restaurants")
    public List<Restaurant> restaurants(Cuisine cuisine) {

        return restaurantService.getRestaurantsByCuisineId(
                cuisine.getCuisineId()
        );
    }

}
