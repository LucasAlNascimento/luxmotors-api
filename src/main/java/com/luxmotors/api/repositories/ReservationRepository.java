package com.luxmotors.api.repositories;

import com.luxmotors.api.domain.reservations.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByUserId(UUID userId);
    boolean existsByCarIdAndAtivaTrue(UUID carId);
}
