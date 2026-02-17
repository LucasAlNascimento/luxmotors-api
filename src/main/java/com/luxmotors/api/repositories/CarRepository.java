package com.luxmotors.api.repositories;

import com.luxmotors.api.domain.cars.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    @Query("""
SELECT c FROM Car c
WHERE (:marcaModelo IS NULL OR 
        LOWER(c.marca) LIKE :marcaModelo OR 
        LOWER(c.modelo) LIKE :marcaModelo)
AND (:ano IS NULL OR c.ano = :ano)
AND (:cor IS NULL OR LOWER(c.cor) LIKE :cor)
AND (:precoDiaria IS NULL OR c.precoDiaria = :precoDiaria)
AND (:disponivel IS NULL OR c.disponivel = :disponivel)
AND (:descricao IS NULL OR LOWER(c.descricao) LIKE :descricao)
""")
    Page<Car> findCars(
            @Param("marcaModelo") String marcaModelo,
            @Param("ano") Integer ano,
            @Param("cor") String cor,
            @Param("precoDiaria") BigDecimal precoDiaria,
            @Param("disponivel") Boolean disponivel,
            @Param("descricao") String descricao,
            Pageable pageable
    );
}
