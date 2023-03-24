package com.example.hamgaja.products.repository;

import com.example.hamgaja.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
