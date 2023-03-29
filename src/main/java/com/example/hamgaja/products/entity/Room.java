package com.example.hamgaja.products.entity;

import com.example.hamgaja.products.dto.RoomRequestDto;
import com.example.hamgaja.products.entity.Product;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // 객실 타입 : 스위트, 등등
    @Column(nullable = false)
    String roomName;

    // 객실 가격
    @Column(nullable = false)
    Long roomPrice;

    // s3 저장 이미지 url
    @Column(nullable = false)
    private String roomImageUrl;

    // 숙소
    @ManyToOne(fetch = FetchType.LAZY)
    Product product;

    public Room(RoomRequestDto roomRequestDto, String roomImageUrl, Product product) {
        this.roomName = roomRequestDto.getRoomName();
        this.roomPrice = Long.parseLong(roomRequestDto.getRoomPrice());
        this.roomImageUrl = roomImageUrl;
        this.product = product;
    }
}
