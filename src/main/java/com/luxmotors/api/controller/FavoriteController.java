package com.luxmotors.api.controller;

import com.luxmotors.api.domain.favorites.Favorite;
import com.luxmotors.api.domain.favorites.FavoriteRequestDTO;
import com.luxmotors.api.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<Favorite> addFavorite(@RequestBody FavoriteRequestDTO favoriteRequestDTO) {
        Favorite favorite = favoriteService.addFavorite(favoriteRequestDTO);
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
