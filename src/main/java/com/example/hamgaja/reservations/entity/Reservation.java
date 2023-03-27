package com.example.hamgaja.reservations.entity;

import com.example.hamgaja.users.entity.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    // 예약 날짜
    @Column(nullable = false)
    private LocalDateTime reservationDate;

    // 체크인 날짜-시각
    @Column(nullable = false)
    private LocalDateTime checkIn;

    // 체크아웃 날짜-시각
    @Column(nullable = false)
    private LocalDateTime checkOut;

}
