package com.example.hamgaja.reservations.repository;

import com.example.hamgaja.reservations.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
