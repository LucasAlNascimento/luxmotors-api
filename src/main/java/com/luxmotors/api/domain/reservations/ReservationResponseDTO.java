package com.luxmotors.api.domain.reservations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationResponseDTO(
        UUID id,
        UUID carId,
        String marca,
        String modelo,
        String imgUrl,
        LocalDate dataInicio,
        LocalDate dataFim,
        BigDecimal valorTotal,
        boolean ativa,
        LocalDateTime dataCriacao) {
}
