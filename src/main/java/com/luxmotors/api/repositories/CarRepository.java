package com.luxmotors.api.repositories;

import com.luxmotors.api.domain.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
}
