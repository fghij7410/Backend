package com.example.hamgaja.reservations.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReservationRequestDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd`T`HH:mm", timezone = "Asia/Seoul")
    LocalDateTime checkIn;     // 체크인 날짜 정보

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd`T`HH:mm", timezone = "Asia/Seoul")
    LocalDateTime checkOut;    // 체크아웃 날짜 정보
}
