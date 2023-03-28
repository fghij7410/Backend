package com.example.hamgaja.products.dto;

import com.example.hamgaja.products.entity.Location;
import com.example.hamgaja.products.entity.ProductType;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRequestDto {
    private String name;
    private String star;
    private String address;
    private String description;
    private String ownerComment;

}
