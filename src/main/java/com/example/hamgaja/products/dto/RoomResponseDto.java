package com.example.hamgaja.products.dto;

import com.example.hamgaja.products.entity.Room;
import lombok.Getter;

@Getter
public class RoomResponseDto {
    String roomName;
    Long roomPrice;
    String roomImageUrl;

    public RoomResponseDto(Room room) {
        this.roomName = room.getRoomName();
        this.roomPrice = room.getRoomPrice();
        this.roomImageUrl = room.getRoomImageUrl();
    }
}
