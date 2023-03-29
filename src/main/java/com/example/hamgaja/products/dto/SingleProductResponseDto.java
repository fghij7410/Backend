package com.example.hamgaja.products.dto;

import com.example.hamgaja.products.entity.Product;
import com.example.hamgaja.products.entity.ProductType;
import lombok.Getter;

import java.util.List;

@Getter
public class SingleProductResponseDto {
    private Long id;
    private String name;
    private String star;
    private String address;
    private String description;
    private ProductType productType;
    private String ownerComment;
    private String imageUrl;
    private List<RoomResponseDto> roomList;


    public SingleProductResponseDto(Product product, List<RoomResponseDto> roomList) {
        this.id = product.getId();
        this.name = product.getName();
        this.star = product.getStar();
        this.address = product.getAddress();
        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
        this.productType = product.getProductType();
        this.ownerComment = product.getOwnerComment();
        this.roomList = roomList;
    }
}
