package com.luxmotors.api.services;

import com.amazonaws.services.s3.AmazonS3;
import com.luxmotors.api.domain.cars.CarRequestDTO;
import com.luxmotors.api.domain.cars.Cars;
import com.luxmotors.api.domain.cars.CarsDetailsDTO;
import com.luxmotors.api.repositories.CarsRepository;
import com.luxmotors.api.repositories.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CarService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private FavoritesRepository favoritesRepository;

    public Cars createCar(CarRequestDTO data) {
        String imgUrl = null;

        if (data.image() != null) {
            imgUrl = this.uploadImg(data.image());
        }

        Cars cars = new Cars();

        cars.setMarca(data.marca());
        cars.setModelo(data.modelo());
        cars.setAno(data.ano());
        cars.setCor(data.cor());
        cars.setPrecoDiaria(data.precoDiaria());
        cars.setDisponivel(data.disponivel());
        cars.setImagemUrl(imgUrl);
        cars.setDescricao(data.descricao());
        cars.setDataCadastro(data.dataCadastro());

        return carsRepository.save(cars);
    }

    private String uploadImg(MultipartFile multipartFile) {
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = this.convertMultipartToFile(multipartFile);
            s3Client.putObject(bucketName, fileName, file);
            file.delete();
            return s3Client.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            System.out.println("Erro ao subir arquivo");
            return "";
        }
    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

    public List<Cars> findAllCars() {
        return carsRepository.findAll();
    }

    public Cars findCarById(UUID carId) {
        return carsRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Carro com id " + carId + " não encontrado"));
    }
}
