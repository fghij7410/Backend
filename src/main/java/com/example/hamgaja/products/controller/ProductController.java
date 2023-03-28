package com.example.hamgaja.products.controller;

import com.example.hamgaja.message.ResponseMessage;
import com.example.hamgaja.products.dto.LocationRequestDto;
import com.example.hamgaja.products.dto.ProductRequestDto;
import com.example.hamgaja.products.dto.ProductResponseDto;
import com.example.hamgaja.products.dto.S3ResponseDto;
import com.example.hamgaja.products.service.ProductService;
import com.example.hamgaja.products.service.S3UploaderService;
import com.example.hamgaja.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final S3UploaderService s3UploaderService;
    //프로덕트 추가
    @PostMapping("/products")
    public ResponseEntity addProduct(ProductRequestDto productRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @RequestParam(value = "fileType") String fileType,
                                     @RequestPart(value = "mainImage") List<MultipartFile> multipartFiles){
        S3ResponseDto s3ResponseDto = s3UploaderService.uploadFiles(fileType, multipartFiles);
        return ResponseMessage.SuccessResponse("숙소 등록 성공",productService.addProduct(userDetails, s3ResponseDto.getUploadFileUrl(), productRequestDto));
    }
    //프로덕트 전체 조회
    @GetMapping("/products")
    public ResponseEntity getAllProduct(){
        return ResponseMessage.SuccessResponse("숙소 전체 조회 성공",productService.getAllProduct());
    }
    //프로덕트 수정
    @PatchMapping("/products/{productId}")
    public ResponseEntity modifyProduct(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody ProductRequestDto productRequestDto,@PathVariable Long productId){

        return ResponseMessage.SuccessResponse(productService.modifyProduct(userDetails,productRequestDto,productId),"");
    }
    //프로덕트 삭제
    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProduct(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long productId){
        String result = productService.deleteProduct(userDetails,productId);
        s3UploaderService.deleteFile(productId);
        return ResponseMessage.SuccessResponse(result,"");
    }
    //프로덕트 상세 조회
    @GetMapping("/products/{productId}")
    public ResponseEntity getProduct(@PathVariable Long productId){
        return ResponseMessage.SuccessResponse("",productService.getProduct(productId));
    }
}
