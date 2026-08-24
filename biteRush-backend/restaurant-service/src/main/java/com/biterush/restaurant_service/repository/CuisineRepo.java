package com.biterush.restaurant_service.repository;

import com.biterush.restaurant_service.model.entity.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CuisineRepo extends JpaRepository<Cuisine, Long> {
}
