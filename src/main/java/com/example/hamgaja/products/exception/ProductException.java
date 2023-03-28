package com.example.hamgaja.products.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductException extends RuntimeException{
    private final ProductErrorCode errorCode;
}
