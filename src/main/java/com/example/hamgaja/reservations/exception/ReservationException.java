package com.example.hamgaja.reservations.exception;

import com.example.hamgaja.users.exception.UserErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationException extends RuntimeException {
    private final UserErrorCode errorCode;
}
