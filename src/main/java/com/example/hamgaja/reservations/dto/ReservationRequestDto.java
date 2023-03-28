package com.example.hamgaja.reservations.dto;

import com.example.hamgaja.products.entity.Product;
import com.example.hamgaja.reservations.entity.Room;
import com.example.hamgaja.users.entity.User;
import lombok.Getter;

@Getter
public class ReservationRequestDto {
    User user;  // 예약자
    Product product; // 숙소
    Room room; // 객실
    String checkIn;     // 체크인 시간
    String checkOut;    // 체크아웃 시간
    Long price; // 가격

}
