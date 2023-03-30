package com.example.hamgaja.products.repository;

import com.example.hamgaja.products.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findAllByProductId(Long id);
}
