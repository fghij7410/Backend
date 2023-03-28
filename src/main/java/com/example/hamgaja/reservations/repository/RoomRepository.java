package com.example.hamgaja.reservations.repository;

import com.example.hamgaja.reservations.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
