package com.luxmotors.api.repositories;

import com.luxmotors.api.domain.cars.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarsRepository extends JpaRepository<Cars, UUID> {
}
