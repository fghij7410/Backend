package com.example.hamgaja.reservations.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class ReservationTimestamped {

    // 예약한 날짜
    @CreatedDate
    private LocalDateTime reservedAt;

    // 수정 날짜
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
