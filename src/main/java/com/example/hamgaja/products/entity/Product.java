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

    @Column(nullable = false,length = 1000)
    private String imageUrl;

    @Column(nullable = false,length = 4000)
    private String description;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductType productType;

    @Column (nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;

    public Product(ProductRequestDto requestDto, ProductType productType) {
        this.name = requestDto.getName();
        this.imageUrl = null;
        this.description = requestDto.getDescription();
        this.productType = productType;
    }
}
