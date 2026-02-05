package com.luxmotors.api.domain.cars;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CarDetailsDTO(
        UUID id,
        String marca,
        String modelo,
        Integer ano,
        String cor,
        BigDecimal precoDiaria,
        boolean disponivel,
        String imagemUrl,
        String descricao,
        LocalDateTime dataCadastro) {
}
