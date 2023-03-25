package com.example.hamgaja.products.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ProductErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_TOKEN(UNAUTHORIZED, "토큰이 유효하지 않습니다"),
    DUPLICATE_LODGING(BAD_REQUEST, "중복된 숙소가 존재합니다"),
    NOT_PROPER_INPUTFORM(BAD_REQUEST, "입력한 형식이 맞지 않습니다."),
    NOT_PROPER_IMAGE(BAD_REQUEST, "입력한 이미지의 형식이 맞지 않습니다."),

//    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    LODGING_NOT_FOUND(NOT_FOUND, "선택한 숙소를 찾을 수 없습니다."),
    ROOM_NOT_FOUND(NOT_FOUND, "선택한 객실 찾을 수 없습니다.")

    ;
    private final HttpStatus httpStatus;
    private final String message;
}
