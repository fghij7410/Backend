package com.example.hamgaja.reservations.controller;

import com.example.hamgaja.reservations.dto.ReservationRequestDto;
import com.example.hamgaja.reservations.dto.ReservationResponseDto;
import com.example.hamgaja.reservations.service.ReservationService;
import com.example.hamgaja.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 예약하기
     */
    @PostMapping
    public ResponseEntity<ReservationResponseDto> addReservation(
            @RequestBody ReservationRequestDto reservationRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(reservationService.addReservation(reservationRequestDto, userDetails.getUser()));
    }

    /**
     * 사용자 예약 조회
     */
    @PostMapping
    public ResponseEntity<ReservationResponseDto> getReservation(
            @RequestBody ReservationRequestDto reservationRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(reservationService.getReservation(reservationRequestDto, userDetails.getUser()));
    }

    /**
     * 예약 수정
     */
    @PatchMapping("/{reservationId}")
    public ResponseEntity<String> editReservation(
            @PathVariable Long reservationId,
            @RequestBody ReservationRequestDto reservationRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(reservationService.editReservation(reservationRequestDto, userDetails.getUser()));
    }

    /**
     * 예약 취소
     */
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> cancelReservation(
            @PathVariable Long reservationId,
            @RequestBody ReservationRequestDto reservationRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(reservationService.cancelReservation(reservationRequestDto, userDetails.getUser()));
    }
}
