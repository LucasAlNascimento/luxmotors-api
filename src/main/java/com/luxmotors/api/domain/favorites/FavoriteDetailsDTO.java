package com.luxmotors.api.domain.favorites;

import com.luxmotors.api.domain.cars.CarDetailsDTO;
import com.luxmotors.api.domain.users.UserDetailsDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record FavoriteDetailsDTO(
        UUID id,
        LocalDateTime dataFavorito,
        List<CarDetailsDTO> car,
        List<UserDetailsDTO> user) {
}
