package com.luxmotors.api.domain.cars;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record CarsDetailsDTO(
        UUID id,
        String marca,
        String modelo,
        Integer ano,
        String cor,
        BigDecimal precoDiaria,
        Boolean disponivel,
        String imagemUrl,
        String descricao,
        Date dataCadastro) {
}
