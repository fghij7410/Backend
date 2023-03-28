package com.example.hamgaja.products.repository;

import com.example.hamgaja.products.entity.S3Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface S3ImageRepository extends JpaRepository<S3Image,Long> {
}
