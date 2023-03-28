package com.example.hamgaja.reservations.service;

import com.example.hamgaja.reservations.dto.ReservationRequestDto;
import com.example.hamgaja.reservations.dto.ReservationResponseDto;
import com.example.hamgaja.reservations.repository.ReservationRepository;
import com.example.hamgaja.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public ReservationResponseDto addReservation(ReservationRequestDto reservationRequestDto, User user) {
        return null;
    }


    @Transactional(readOnly = true)
    public ReservationResponseDto getReservation(ReservationRequestDto reservationRequestDto, User user) {
        return null;
    }

    @Transactional
    public String editReservation(ReservationRequestDto reservationRequestDto, User user) {
        return null;
    }

    @Transactional
    public String cancelReservation(ReservationRequestDto reservationRequestDto, User user) {
        return null;
    }

}
