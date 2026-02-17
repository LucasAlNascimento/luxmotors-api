package com.luxmotors.api.services;

import com.luxmotors.api.domain.cars.Car;
import com.luxmotors.api.domain.favorites.Favorite;
import com.luxmotors.api.domain.favorites.FavoriteRequestDTO;
import com.luxmotors.api.domain.users.User;
import com.luxmotors.api.repositories.CarRepository;
import com.luxmotors.api.repositories.FavoriteRepository;
import com.luxmotors.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public Favorite addFavorite(FavoriteRequestDTO favoriteRequestDTO) {
        favoriteRepository.findByUserIdAndCarId(favoriteRequestDTO.userId(), favoriteRequestDTO.carId())
                .ifPresent(f -> {
                    throw new IllegalArgumentException("Carro já está nos favoritos!");
                });

        Car car = carRepository.findById(favoriteRequestDTO.carId())
                .orElseThrow(() -> new IllegalArgumentException("Carro não encontrado"));
        User user = userRepository.findById(favoriteRequestDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Favorite favorite = Favorite.builder()
                .car(car)
                .user(user)
                .dataFavorito(LocalDateTime.now())
                .build();

        return favoriteRepository.save(favorite);
    }

    public List<Favorite> listFavorites(UUID userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public void removeFavorite(UUID userId, UUID carId) {
        Favorite favorite = favoriteRepository.findByUserIdAndCarId(userId, carId)
                .orElseThrow(() -> new IllegalArgumentException("Favorito não encontrado"));
        favoriteRepository.delete(favorite);
    }
}
