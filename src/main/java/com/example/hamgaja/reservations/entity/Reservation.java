package com.example.hamgaja.reservations.entity;

import com.example.hamgaja.products.entity.Product;
import com.example.hamgaja.reservations.dto.ReservationRequestDto;
import com.example.hamgaja.users.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reservation extends ReservationTimestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // 예약 유저
    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    // 객실
    @ManyToOne(fetch = FetchType.LAZY)
    Room room;

    // 체크인 날짜-시각
    @Column(nullable = false)
    private LocalDateTime checkIn;

    // 체크아웃 날짜-시각
    @Column(nullable = false)
    private LocalDateTime checkOut;

    public Reservation(ReservationRequestDto reservationRequestDto, Room room, User user) {
        this.room = room;
        this.checkIn = reservationRequestDto.getCheckIn();
        this.checkOut = reservationRequestDto.getCheckOut();
        this.user = user;
    }
}
