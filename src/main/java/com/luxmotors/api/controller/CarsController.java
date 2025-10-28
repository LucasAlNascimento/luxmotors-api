package com.luxmotors.api.controller;

import com.luxmotors.api.domain.cars.CarRequestDTO;
import com.luxmotors.api.domain.cars.Cars;
import com.luxmotors.api.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

    @Autowired
    private CarService carService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Cars> create(@RequestParam("marca") String marca,
                                       @RequestParam("modelo") String modelo,
                                       @RequestParam("ano") Integer ano,
                                       @RequestParam("cor") String cor,
                                       @RequestParam("precoDiaria") BigDecimal precoDiaria,
                                       @RequestParam("disponivel") Boolean disponivel,
                                       @RequestParam("image") MultipartFile image,
                                       @RequestParam("descricao") String descricao,
                                       @RequestParam("dataCadastro") Date dataCadastro) {
        CarRequestDTO carRequestDTO = new CarRequestDTO(marca, modelo, ano, cor, precoDiaria, disponivel, image, descricao, dataCadastro);
        Cars newCar = this.carService.createCar(carRequestDTO);
        return ResponseEntity.ok(newCar);
    }

    @GetMapping
    public ResponseEntity<List<Cars>> findAllCars() {
        List<Cars> cars = carService.findAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Cars> findCarById(@PathVariable UUID carId) {
        Cars cars = carService.findCarById(carId);
        return ResponseEntity.ok(cars);
    }
}
