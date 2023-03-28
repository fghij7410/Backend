package com.example.hamgaja.reservations.dto;

import com.example.hamgaja.products.entity.Product;
import com.example.hamgaja.reservations.entity.Room;
import com.example.hamgaja.users.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationRequestDto {
    Long price; // 가격
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm", timezone = "Asia/Seoul")
    LocalDateTime checkIn;     // 체크인 날짜 정보

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm", timezone = "Asia/Seoul")
    LocalDateTime checkOut;    // 체크아웃 날짜 정보
}
