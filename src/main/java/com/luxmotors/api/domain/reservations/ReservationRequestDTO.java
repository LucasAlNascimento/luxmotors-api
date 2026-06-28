package com.luxmotors.api.domain.reservations;

import java.time.LocalDate;
import java.util.UUID;

public record ReservationRequestDTO(
        UUID userId,
        UUID carId,
        LocalDate dataInicio,
        LocalDate dataFim) {
}
