package com.example.hamgaja.products.controller;

import com.example.hamgaja.message.ResponseMessage;
import com.example.hamgaja.products.dto.*;
import com.example.hamgaja.products.service.ProductService;
import com.example.hamgaja.products.service.S3UploaderService;
import com.example.hamgaja.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    private final S3UploaderService s3UploaderService;

    //프로덕트 추가
    @PostMapping("/products")
    public ResponseEntity addProduct(ProductRequestDto productRequestDto,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        productRequestDto.getRoomList().forEach(x ->
        {
            log.info("roomName={}", x.getRoomName());
            log.info("roomPrice={}", x.getRoomPrice());
            log.info("roomImage={}", x.getRoomImage());
        });
        List<MultipartFile> mainImages = productRequestDto.getMainImage();
        List<MultipartFile> roomImages = productRequestDto.getRoomList().stream().map(RoomRequestDto::getRoomImage).toList();
        List<S3ResponseDto> s3MainImageUrlList = s3UploaderService.uploadFiles(productRequestDto.getProductType(), mainImages);
        List<S3ResponseDto> s3RoomImageUrlList = s3UploaderService.uploadFiles(productRequestDto.getProductType(), roomImages);
        return ResponseMessage.SuccessResponse("숙소 등록 성공",
                productService.addProduct(userDetails, s3MainImageUrlList, s3RoomImageUrlList, productRequestDto));

    }

    //프로덕트 전체 조회
    @GetMapping("/products")
    public ResponseEntity getAllProduct() {
        return ResponseMessage.SuccessResponse("숙소 전체 조회 성공", productService.getAllProduct());
    }

    //프로덕트 수정
    @PatchMapping("/products/{productId}")

    public ResponseEntity modifyProduct(@AuthenticationPrincipal UserDetailsImpl userDetails, ProductRequestDto productRequestDto,@PathVariable Long productId,
                                        @RequestParam(value = "fileType") String fileType,
                                        @RequestPart(value = "mainImage") List<MultipartFile> multipartFiles){

        return ResponseMessage.SuccessResponse(productService.modifyProduct(userDetails,productRequestDto,productId,fileType,multipartFiles),"");

    }

    //프로덕트 삭제
    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProduct(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long productId) {
        String result = productService.deleteProduct(userDetails, productId);
        s3UploaderService.deleteFile(productId);
        return ResponseMessage.SuccessResponse(result, "");

    }

    //프로덕트 상세 조회
    @GetMapping("/products/{productId}")
    public ResponseEntity getProduct(@PathVariable Long productId) {
        return ResponseMessage.SuccessResponse("", productService.getProduct(productId));
    }
}
