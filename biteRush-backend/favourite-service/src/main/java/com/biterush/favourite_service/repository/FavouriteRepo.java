package com.biterush.favourite_service.repository;

import com.biterush.favourite_service.model.entity.Favourite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavouriteRepo extends MongoRepository<Favourite, String> {
    List<Favourite> findByUserId(UUID userId);
}
