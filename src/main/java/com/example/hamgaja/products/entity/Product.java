package com.example.hamgaja.products.entity;

import com.example.hamgaja.products.dto.ProductRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String star;
    @Column(nullable = false)
    private float score;

    @Column(nullable = false,length = 4000)
    private String description;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductType productType;

    @Column(nullable = false)
    private String ownerComment;
    //product의 위치
    @Column(nullable = false)
    private String address;
    //위치 기반 시스템의 위치
    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;

    public Product(String name, String star, float score, String description, String price, ProductType productType, String ownerComment, String address, Location location) {
        this.name = name;
        this.star = star;
        this.score = score;
        this.description = description;
        this.price = price;
        this.productType = productType;
        this.ownerComment = ownerComment;
        this.address = address;
        this.location = location;
    }
}
