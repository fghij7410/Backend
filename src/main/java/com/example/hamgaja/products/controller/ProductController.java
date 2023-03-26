package com.example.hamgaja.products.controller;

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

    @PostMapping("/products")
    public ResponseEntity addProduct(HttpServletRequest request, ProductRequestDto productRequestDto){

        return ResponseEntity.ok("응답성공");

    }

    @GetMapping("/products")
    public ResponseEntity getAllProduct(){
        return null;
    }

    @PatchMapping("/products/{productId}")
    public ResponseEntity modifyProduct(){
        return null;
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProduct(){
        return null;
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity getProduct(){
        return null;
    }
}
