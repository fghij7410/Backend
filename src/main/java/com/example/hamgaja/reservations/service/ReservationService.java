package com.example.hamgaja.reservations.service;

import com.example.hamgaja.products.repository.RoomRepository;
import com.example.hamgaja.reservations.dto.ReservationRequestDto;
import com.example.hamgaja.reservations.dto.ReservationResponseDto;
import com.example.hamgaja.reservations.entity.Reservation;
import com.example.hamgaja.products.entity.Room;
import com.example.hamgaja.reservations.exception.ReservationErrorCode;
import com.example.hamgaja.reservations.exception.ReservationException;
import com.example.hamgaja.reservations.repository.ReservationRepository;
import com.example.hamgaja.security.UserDetailsImpl;
import com.example.hamgaja.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public String addReservation(Long roomId,
                                 ReservationRequestDto reservationRequestDto,
                                 UserDetailsImpl userDetails) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(()-> new ReservationException(ReservationErrorCode.ROOM_NOT_FOUND));

        User user = userDetails.getUser();

        Reservation reservation = new Reservation(reservationRequestDto, room, user);
        reservationRepository.save(reservation);
        return "예약 성공";
    }


    @Transactional(readOnly = true)
    public ReservationResponseDto getReservation(ReservationRequestDto reservationRequestDto, UserDetailsImpl userDetails) {
        return null;
    }

    @Transactional
    public String editReservation(Long reservationId, ReservationRequestDto reservationRequestDto, UserDetailsImpl userDetails) {
        return null;
    }

    @Transactional
    public String cancelReservation(Long reservationId, ReservationRequestDto reservationRequestDto, UserDetailsImpl userDetails) {
        return null;
    }

}
