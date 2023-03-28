package com.example.hamgaja.products.dto;

import com.example.hamgaja.products.entity.Location;
import com.example.hamgaja.products.entity.ProductType;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ProductRequestDto {
    private String name;
    private String star;
    private float score;
    private String address;
    private String description;
    private String price;
    private ProductType productType;
    private String ownerComment;

}
