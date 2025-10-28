package com.luxmotors.api.repositories;

import com.luxmotors.api.domain.favorites.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FavoritesRepository extends JpaRepository<Favorites, UUID> {
    List<Favorites> findByUserId(UUID userId);
    Optional<Favorites> findByUserIdAndCarId(UUID userId, UUID carId);
}
