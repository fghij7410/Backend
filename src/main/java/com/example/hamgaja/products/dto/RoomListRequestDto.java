package com.example.hamgaja.products.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomListRequestDto {
    List<RoomRequestDto> roomList;
}
