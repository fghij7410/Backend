package com.example.hamgaja.products.dto;

import lombok.Getter;


@Getter
public class LocationRequestDto {
    private String address;
    // depth1 : 시도 단위
    private String depth1;
    // depth2 : 시군구 단위
    private String depth2;
    // depth3 : 읍면동 단위
    private String depth3;
}
