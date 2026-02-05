package com.luxmotors.api.controller;

import com.luxmotors.api.domain.favorites.Favorite;
import com.luxmotors.api.services.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private FavoriteService favoriteService;

    @PostMapping("/{userId}/{carId}")
    public ResponseEntity<Favorite> addFavorite(@PathVariable UUID userId, @PathVariable UUID carId) {
        Favorite favorite = favoriteService.addFavorite(userId, carId);
        return ResponseEntity.ok(favorite);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favorite>> listFavorites(@PathVariable UUID userId) {
        return ResponseEntity.ok(favoriteService.listFavorites(userId));
    }

    @DeleteMapping("/{userId}/{carId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable UUID userId, @PathVariable UUID carId) {
        favoriteService.removeFavorite(userId, carId);
        return ResponseEntity.noContent().build();
    }
}
