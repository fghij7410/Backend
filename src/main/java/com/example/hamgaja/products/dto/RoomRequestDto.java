package com.example.hamgaja.products.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RoomRequestDto {
    String roomName;
    MultipartFile roomImage;
    String roomPrice;
}
