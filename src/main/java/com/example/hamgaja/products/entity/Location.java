package com.example.hamgaja.products.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    // depth1 : 시도 단위
    @Column(nullable = false, name = "sido")
    private String depth1;

    // depth2 : 시군구 단위
    @Column(nullable = false, name = "sigungu")
    private String depth2;

    // depth3 : 읍면동 단위
    @Column(nullable = false, name = "eupmyundong")
    private String depth3;
}
