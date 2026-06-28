package com.luxmotors.api.controller;

import com.luxmotors.api.domain.reservations.ReservationRequestDTO;
import com.luxmotors.api.domain.reservations.ReservationResponseDTO;
import com.luxmotors.api.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> create(@RequestBody ReservationRequestDTO data) {
        return ResponseEntity.ok(reservationService.createReservation(data));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>> list(@PathVariable UUID userId) {
        return ResponseEntity.ok(reservationService.listReservations(userId));
    }
}
