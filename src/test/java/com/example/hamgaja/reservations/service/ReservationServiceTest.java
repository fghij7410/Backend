package com.example.hamgaja.reservations.service;

import com.example.hamgaja.products.entity.Room;
import com.example.hamgaja.reservations.entity.Reservation;
import com.example.hamgaja.reservations.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.doReturn;

class ReservationServiceTest {
    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);

    @Test
    void addReservation() {
        // given

        // when

        // then
    }

//    private Room room() {
//
//    }
}