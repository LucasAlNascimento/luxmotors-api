package com.luxmotors.api.domain.favorites;

import java.util.UUID;

public record FavoriteRequestDTO(
        UUID userId,
        UUID carId) {
}
