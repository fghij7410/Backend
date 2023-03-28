package com.example.hamgaja.reservations.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ReservationErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_TOKEN(UNAUTHORIZED, "토큰이 유효하지 않습니다"),
    NOT_HAVE_PERMISSION(BAD_REQUEST, "권한이 없습니다."),


    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    RESERVATION_NOT_FOUND(NOT_FOUND, "등록된 예약이 없습니다"),
    ROOM_NOT_FOUND(NOT_FOUND, "객실을 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
