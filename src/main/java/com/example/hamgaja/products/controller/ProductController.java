package com.example.hamgaja.products.controller;

import com.example.hamgaja.message.ResponseMessage;
import com.example.hamgaja.products.dto.LocationRequestDto;
import com.example.hamgaja.products.dto.ProductRequestDto;
import com.example.hamgaja.products.dto.ProductResponseDto;
import com.example.hamgaja.products.service.ProductService;
import com.example.hamgaja.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    //프로덕트 추가
    @PostMapping("/products")
    public ResponseEntity addProduct(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody ProductRequestDto productRequestDto){
        return ResponseMessage.SuccessResponse("숙소 등록 성공",productService.addProduct(userDetails.getUser(),productRequestDto));
    }
    //프로덕트 전체 조회
    @GetMapping("/products")
    public ResponseEntity getAllProduct(){
        return ResponseMessage.SuccessResponse("숙소 전체 조회 성공",productService.getAllProduct());
    }
    //프로덕트 수정
    @PatchMapping("/products/{productId}")
    public ResponseEntity modifyProduct(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody ProductRequestDto productRequestDto,@PathVariable Long productId){
        return ResponseMessage.SuccessResponse(productService.modifyProduct(userDetails.getUser(),productRequestDto,productId),"");
    }
    //프로덕트 삭제
    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProduct(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long productId){
        return ResponseMessage.SuccessResponse(productService.deleteProduct(userDetails.getUser(),productId),"");
    }
    //프로덕트 상세 조회
    @GetMapping("/products/{productId}")
    public ResponseEntity getProduct(@PathVariable Long productId){
        return ResponseMessage.SuccessResponse("",productService.getProduct(productId));
    }
}
