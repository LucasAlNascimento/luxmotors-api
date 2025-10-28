package com.luxmotors.api.controller;

import com.luxmotors.api.domain.favorites.Favorites;
import com.luxmotors.api.domain.favorites.FavoritesDetailsDTO;
import com.luxmotors.api.services.FavoritesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {

    private FavoritesService favoritesService;

    @PostMapping("/{userId}/{carId}")
    public ResponseEntity<Favorites> addFavorite(
            @PathVariable UUID userId,
            @PathVariable UUID carId) {
        Favorites favorite = favoritesService.addFavorite(userId, carId);
        return ResponseEntity.ok(favorite);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favorites>> listFavorites(@PathVariable UUID userId) {
        return ResponseEntity.ok(favoritesService.listFavorites(userId));
    }

    @DeleteMapping("/{userId}/{carId}")
    public ResponseEntity<Void> removeFavorite(
            @PathVariable UUID userId,
            @PathVariable UUID carId) {
        favoritesService.removeFavorite(userId, carId);
        return ResponseEntity.noContent().build();
    }
}
