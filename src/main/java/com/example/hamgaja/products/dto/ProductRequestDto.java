package com.example.hamgaja.products.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ProductRequestDto {
    private String name;
    private MultipartFile image;
    private String description;

}
