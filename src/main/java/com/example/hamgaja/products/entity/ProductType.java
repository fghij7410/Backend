package com.example.hamgaja.products.entity;

public enum ProductType {
    HOTEL (1),
    MOTEL (2);

    private final Integer code;

    ProductType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
