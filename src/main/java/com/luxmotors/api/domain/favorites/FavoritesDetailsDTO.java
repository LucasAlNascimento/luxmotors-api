package com.luxmotors.api.domain.favorites;

import com.luxmotors.api.domain.cars.CarsDetailsDTO;
import com.luxmotors.api.domain.users.UsersDetailsDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record FavoritesDetailsDTO(
        UUID id,
        Date dataFavorito,
        List<CarsDetailsDTO> car,
        List<UsersDetailsDTO> user) {
}
