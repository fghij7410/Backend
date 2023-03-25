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

    @PostMapping
    public ResponseEntity addProduct(HttpServletRequest request, ProductRequestDto productRequestDto){
        return null;
    }

    @GetMapping
    public ResponseEntity getAllProduct(){
        return null;
    }

    @PatchMapping
    public ResponseEntity modifyProduct(){
        return null;
    }

    @DeleteMapping
    public ResponseEntity deleteProduct(){
        return null;
    }

    @GetMapping
    public ResponseEntity getProduct(){
        return null;
    }
}
