package com.biterush.favourite_service.service;

import com.biterush.favourite_service.model.dto.request.FavouriteRequestDTO;
import com.biterush.favourite_service.model.dto.response.FavouriteResponseDTO;
import com.biterush.favourite_service.model.entity.Favourite;

import java.util.List;
import java.util.UUID;

public interface FavouriteService {
    List<Favourite> findByUserId(UUID userId);
    Favourite findById(String id);
    FavouriteResponseDTO addFavourite(FavouriteRequestDTO requestDTO);
    String removeFavourite(String id);
}
