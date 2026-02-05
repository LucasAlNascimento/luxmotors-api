package com.luxmotors.api.domain.cars;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CarRequestDTO(
        String marca,
        String modelo,
        Integer ano,
        String cor,
        BigDecimal precoDiaria,
        boolean disponivel,
        String descricao,
        LocalDateTime dataCadastro) {
}
