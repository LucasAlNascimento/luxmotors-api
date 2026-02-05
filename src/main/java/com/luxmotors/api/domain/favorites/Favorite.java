package com.luxmotors.api.domain.favorites;

import com.luxmotors.api.domain.cars.Car;
import com.luxmotors.api.domain.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "favorites")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataFavorito;

    @PrePersist
    private void prePersist() {
        this.dataFavorito = LocalDateTime.now();
    }
}
