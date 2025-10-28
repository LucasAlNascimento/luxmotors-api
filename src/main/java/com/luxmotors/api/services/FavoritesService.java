package com.luxmotors.api.services;

import com.luxmotors.api.domain.cars.Cars;
import com.luxmotors.api.domain.favorites.Favorites;
import com.luxmotors.api.domain.users.Users;
import com.luxmotors.api.repositories.CarsRepository;
import com.luxmotors.api.repositories.FavoritesRepository;
import com.luxmotors.api.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Favorites addFavorite(UUID userId, UUID carId) {
        favoritesRepository.findByUserIdAndCarId(userId, carId)
                .ifPresent(f -> {
                    throw new IllegalArgumentException("Carro já está nos favoritos!");
                });

        Cars car = carsRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Carro não encontrado"));
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Favorites favorite = new Favorites();
        favorite.setCar(car);
        favorite.setUser(user);
        favorite.setDataFavorito(new Date());

        return favoritesRepository.save(favorite);
    }

    public List<Favorites> listFavorites(UUID userId) {
        return favoritesRepository.findByUserId(userId);
    }

    public void removeFavorite(UUID userId, UUID carId) {
        Favorites favorite = favoritesRepository.findByUserIdAndCarId(userId, carId)
                .orElseThrow(() -> new IllegalArgumentException("Favorito não encontrado"));
        favoritesRepository.delete(favorite);
    }
}
