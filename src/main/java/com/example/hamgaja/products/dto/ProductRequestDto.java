package com.example.hamgaja.products.dto;

import com.example.hamgaja.products.entity.Category;
import com.example.hamgaja.products.entity.Location;
import com.example.hamgaja.products.entity.ProductType;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
public class ProductRequestDto {
    private String name;
    private String star;
    private String address;
    private String description;
    private String ownerComment;
    private String productType;
    Map<String, Boolean> category;
    List<MultipartFile> mainImage;
    List<RoomRequestDto> roomList;
}
