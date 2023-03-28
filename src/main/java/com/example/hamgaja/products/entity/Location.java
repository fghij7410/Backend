package com.example.hamgaja.products.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String address;

    // depth1 : 시도 단위
    @Column(nullable = true, name = "sido")
    private String depth1;

    // depth2 : 시군구 단위
    @Column(nullable = true, name = "sigungu")
    private String depth2;

    // depth3 : 읍면동 단위
    @Column(nullable = true, name = "eupmyundong")
    private String depth3;

    public Location(String address, String depth1, String depth2) {
        this.address = address;
        this.depth1 = depth1;
        this.depth2 = depth2;
    }
}
