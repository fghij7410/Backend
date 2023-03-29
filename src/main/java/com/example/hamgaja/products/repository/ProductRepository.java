package com.example.hamgaja.products.repository;

import com.example.hamgaja.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    /*@Query(SELECT * FROM product INNER JOIN room ON product_id = product_id)
    void deleteAllBy(Long productId);*/

}
