package com.example.hamgaja.reservations.entity;

import com.example.hamgaja.products.entity.Product;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // 숙소
    @ManyToOne(fetch = FetchType.LAZY)
    Product product;

    // 객실 타입 : 스위트, 등등
    @Column(nullable = false)
    String type;

    // 객실 가격
    @Column(nullable = false)
    Long price;
}
