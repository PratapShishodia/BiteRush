package com.biterush.favourite_service.controller;

import com.biterush.favourite_service.model.dto.response.FavouriteResponseDTO;
import com.biterush.favourite_service.model.entity.Favourite;
import com.biterush.favourite_service.service.FavouriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FavouriteGraphQLController {

    private final FavouriteService favouriteService;

    @QueryMapping
    public ResponseEntity<Favourite> getFavourite(@Argument String id){
        return ResponseEntity.ok(favouriteService.findById(id));
    }

    @QueryMapping
    public ResponseEntity<List<Favourite>> getFavourites(@Argument UUID userId){
        return ResponseEntity.ok(favouriteService.findByUserId(userId));
    }

}
