package com.example.hamgaja.products.controller;

import com.example.hamgaja.products.dto.LocationRequestDto;
import com.example.hamgaja.products.dto.ProductRequestDto;
import com.example.hamgaja.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    //프로덕트 추가
    @PostMapping("/products")

    public ResponseEntity addProduct(HttpServletRequest request, ProductRequestDto productRequestDto){
        return ResponseEntity.ok("응답성공");
    }
    //프로덕트 전체 조회
    /*@GetMapping("/products")
    public ResponseEntity getAllProduct(@RequestBody ProductRequestDto productRequestDto, LocationRequestDto locationRequestDto){
        return productService.getAllProduct(productRequestDto,locationRequestDto);
    }
    //프로덕트 수정
    @PatchMapping("/products/{productId}")
    public ResponseEntity modifyProduct(@RequestBody ProductRequestDto productRequestDto){
        return productService.modifyProduct(productRequestDto);
    }
    //프로덕트 삭제
    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProduct(@RequestBody ProductRequestDto productRequestDto){
        return productService.deleteProduct(productRequestDto);
    }
    //프로덕트 상세 조회
    @GetMapping("/products/{productId}")
    public ResponseEntity getProduct(@RequestBody ProductRequestDto productRequestDto){
        return productService.getProduct(productRequestDto);
    }*/
}
