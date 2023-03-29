package com.example.hamgaja.products.repository;

import com.example.hamgaja.products.entity.Room;
import com.example.hamgaja.products.entity.S3Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface S3ImageRepository extends JpaRepository<S3Image,Long> {

    //void deleteAllByUploadFileUrl(List<Room> rooms);
}
