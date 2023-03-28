package com.example.hamgaja.products.dto;

import com.example.hamgaja.products.entity.Product;
import com.example.hamgaja.products.entity.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private String star;
    private float score;
    private String address;
    private String description;
    private String price;
    private ProductType productType;
    private String ownerComment;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.star = product.getStar();
        this.score = product.getScore();
        this.address = product.getAddress();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.productType = product.getProductType();
        this.ownerComment = product.getOwnerComment();
    }
}
