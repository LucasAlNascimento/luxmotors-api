package com.luxmotors.api.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.luxmotors.api.domain.cars.Car;
import com.luxmotors.api.domain.cars.CarRequestDTO;
import com.luxmotors.api.repositories.CarRepository;
import com.luxmotors.api.repositories.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    @Value("${aws.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    String uploadImg(MultipartFile file) {
        try {
            String originalName = Optional.ofNullable(file.getOriginalFilename())
                    .orElse("file");

            String safeName = originalName.replaceAll("[^a-zA-Z0-9.\\-]", "_");

            String fileName = UUID.randomUUID() + "-" + safeName;

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            try (InputStream inputStream = file.getInputStream()) {
                s3Client.putObject(bucketName, fileName, inputStream, metadata);
            }

            return s3Client.getUrl(bucketName, fileName).toString();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao subir arquivo para o S3", e);
        }
    }
}
