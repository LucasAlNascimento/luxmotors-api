package com.luxmotors.api.domain.cars;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue
    private UUID id;
    private String marca;
    private String modelo;
    private Integer ano;
    private String cor;
    private BigDecimal precoDiaria;
    private Boolean disponivel;
    private String imagemUrl;
    private String descricao;
    private Date dataCadastro;
}
