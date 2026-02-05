package com.luxmotors.api.services;

import com.luxmotors.api.domain.cars.CarRequestDTO;
import com.luxmotors.api.domain.cars.Car;
import com.luxmotors.api.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final S3Service s3Service;

    public Car createCar(CarRequestDTO data) {
        Car cars = Car.builder()
                .marca(data.marca())
                .modelo(data.modelo())
                .ano(data.ano())
                .cor(data.cor())
                .precoDiaria(data.precoDiaria())
                .disponivel(data.disponivel())
                .descricao(data.descricao())
                .dataCadastro(data.dataCadastro())
                .build();

        return carRepository.save(cars);
    }

    public String uploadImage(UUID carId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Arquivo inválido");
        }

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Carro não encontrado"));

        String imageUrl = s3Service.uploadImg(file);

        car.setImgUrl(imageUrl);

        return carRepository.save(car).getImgUrl();
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Car findCarById(UUID carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Carro com id " + carId + " não encontrado"));
    }
}
