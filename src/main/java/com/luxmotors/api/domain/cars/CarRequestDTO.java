package com.luxmotors.api.domain.cars;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

public record CarRequestDTO(
        String marca,
        String modelo,
        Integer ano,
        String cor,
        BigDecimal precoDiaria,
        Boolean disponivel,
        MultipartFile image,
        String descricao,
        Date dataCadastro) {
}
