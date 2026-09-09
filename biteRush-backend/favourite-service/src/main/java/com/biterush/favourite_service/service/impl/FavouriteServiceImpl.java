package com.biterush.favourite_service.service.impl;

import com.biterush.favourite_service.model.dto.request.FavouriteRequestDTO;
import com.biterush.favourite_service.model.dto.response.FavouriteResponseDTO;
import com.biterush.favourite_service.model.entity.Favourite;
import com.biterush.favourite_service.model.mapper.FavouriteDTOMapper;
import com.biterush.favourite_service.repository.FavouriteRepo;
import com.biterush.favourite_service.service.FavouriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavouriteServiceImpl implements FavouriteService {

    private final FavouriteRepo favouriteRepo;

    @Override
    public List<Favourite> findByUserId(UUID userId) {
        return favouriteRepo.findByUserId(userId);
    }

    @Override
    public Favourite findById(String id) {
        return favouriteRepo.findById(id).orElseThrow(()-> new RuntimeException("Favourite not found"));
    }

    @Override
    public FavouriteResponseDTO addFavourite(FavouriteRequestDTO requestDTO) {
        Favourite fav = FavouriteDTOMapper.toEntity(requestDTO);
        fav.setCreatedAt(LocalDateTime.now());
        return FavouriteDTOMapper.toDTO(favouriteRepo.save(fav));
    }

    @Override
    public String removeFavourite(String id) {
        Favourite fav = favouriteRepo.findById(id).orElseThrow(()-> new RuntimeException("Favourite not found"));
        favouriteRepo.delete(fav);
        return "Favourite removed";
    }
}
