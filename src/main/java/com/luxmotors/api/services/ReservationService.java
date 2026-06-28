package com.luxmotors.api.services;

import com.luxmotors.api.domain.cars.Car;
import com.luxmotors.api.domain.reservations.Reservation;
import com.luxmotors.api.domain.reservations.ReservationRequestDTO;
import com.luxmotors.api.domain.reservations.ReservationResponseDTO;
import com.luxmotors.api.domain.users.User;
import com.luxmotors.api.repositories.CarRepository;
import com.luxmotors.api.repositories.ReservationRepository;
import com.luxmotors.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public ReservationResponseDTO createReservation(ReservationRequestDTO data) {
        if (data.dataFim().isBefore(data.dataInicio()) || data.dataFim().isEqual(data.dataInicio())) {
            throw new IllegalArgumentException("Data fim deve ser posterior à data início");
        }

        if (reservationRepository.existsByCarIdAndAtivaTrue(data.carId())) {
            throw new IllegalArgumentException("Carro já possui uma reserva ativa");
        }

        Car car = carRepository.findById(data.carId())
                .orElseThrow(() -> new IllegalArgumentException("Carro não encontrado"));

        User user = userRepository.findById(data.userId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        long dias = ChronoUnit.DAYS.between(data.dataInicio(), data.dataFim());
        BigDecimal valorTotal = car.getPrecoDiaria().multiply(BigDecimal.valueOf(dias));

        Reservation reservation = Reservation.builder()
                .car(car)
                .user(user)
                .dataInicio(data.dataInicio())
                .dataFim(data.dataFim())
                .valorTotal(valorTotal)
                .ativa(true)
                .build();

        reservationRepository.save(reservation);

        car.setDisponivel(false);
        carRepository.save(car);

        return toDTO(reservation, car);
    }

    public List<ReservationResponseDTO> listReservations(UUID userId) {
        return reservationRepository.findByUserId(userId)
                .stream()
                .map(r -> toDTO(r, r.getCar()))
                .toList();
    }

    private ReservationResponseDTO toDTO(Reservation reservation, Car car) {
        return new ReservationResponseDTO(
                reservation.getId(),
                car.getId(),
                car.getMarca(),
                car.getModelo(),
                car.getImgUrl(),
                reservation.getDataInicio(),
                reservation.getDataFim(),
                reservation.getValorTotal(),
                reservation.isAtiva(),
                reservation.getDataCriacao()
        );
    }
}
