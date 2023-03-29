package com.example.hamgaja.products.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 숙소-카테고리 연결 테이블
 */
@Entity
@NoArgsConstructor
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Product product;

    @ManyToOne
    Category category;

    public ProductCategory(Product product, Category category) {
        this.product = product;
        this.category = category;
    }
}
