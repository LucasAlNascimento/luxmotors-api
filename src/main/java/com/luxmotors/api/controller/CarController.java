package com.luxmotors.api.controller;

import com.luxmotors.api.domain.cars.CarRequestDTO;
import com.luxmotors.api.domain.cars.Car;
import com.luxmotors.api.domain.users.UserRequestDTO;
import com.luxmotors.api.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Car> create(@RequestBody CarRequestDTO carRequestDTO) {
        Car newCar = carService.createCar(carRequestDTO);
        return ResponseEntity.ok(newCar);
    }

    @PutMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        String imageUrl = carService.uploadImage(id, file);
        return ResponseEntity.ok(imageUrl);
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAllCars() {
        List<Car> cars = carService.findAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> findCarById(@PathVariable UUID carId) {
        Car cars = carService.findCarById(carId);
        return ResponseEntity.ok(cars);
    }
}
