package com.example.hamgaja.products.repository;

import com.example.hamgaja.products.entity.Product;
import com.example.hamgaja.products.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByProductId(Long productId);
}
