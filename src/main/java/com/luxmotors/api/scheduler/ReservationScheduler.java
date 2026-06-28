package com.luxmotors.api.scheduler;

import com.luxmotors.api.domain.cars.Car;
import com.luxmotors.api.domain.reservations.Reservation;
import com.luxmotors.api.repositories.CarRepository;
import com.luxmotors.api.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationScheduler {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;

    @Scheduled(cron = "${scheduler.reservation.cron}")
    public void finalizarReservasExpiradas() {
        List<Reservation> expiradas = reservationRepository
                .findByAtivaTrueAndDataFimBefore(LocalDate.now());

        expiradas.forEach(reservation -> {
            reservation.setAtiva(false);
            reservationRepository.save(reservation);

            Car car = reservation.getCar();
            car.setDisponivel(true);
            carRepository.save(car);
        });
    }
}
