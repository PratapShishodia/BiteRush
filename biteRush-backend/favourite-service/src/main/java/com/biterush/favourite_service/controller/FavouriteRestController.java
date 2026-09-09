package com.biterush.favourite_service.controller;

import com.biterush.favourite_service.model.dto.request.FavouriteRequestDTO;
import com.biterush.favourite_service.model.dto.response.FavouriteResponseDTO;
import com.biterush.favourite_service.service.FavouriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favourite")
public class FavouriteRestController {

    private final FavouriteService favouriteService;

    @PostMapping
    public ResponseEntity<FavouriteResponseDTO> addFavourite(FavouriteRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(favouriteService.addFavourite(requestDTO));
    }

    @DeleteMapping
    public ResponseEntity<String> removeFavourite(String id) {
        return ResponseEntity.status(HttpStatus.OK).body(favouriteService.removeFavourite(id));
    }

}
