package com.example.hamgaja.products.service;

import com.example.hamgaja.products.dto.ProductRequestDto;
import com.example.hamgaja.products.dto.ProductResponseDto;
import com.example.hamgaja.products.entity.Product;
import com.example.hamgaja.products.entity.ProductType;
import com.example.hamgaja.products.exception.ProductErrorCode;
import com.example.hamgaja.products.exception.ProductException;
import com.example.hamgaja.products.repository.ProductRepository;
import com.example.hamgaja.users.entity.User;
import com.example.hamgaja.users.exception.UserErrorCode;
import com.example.hamgaja.users.exception.UserException;
import com.example.hamgaja.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.hamgaja.users.entity.UserRoleEnum.BUSINESS;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;
    //프로덕트 추가
    @Transactional
    public ProductResponseDto addProduct(User user,String imageUrl ,ProductRequestDto productRequestDto) {
        user = userRepository.findByIdAndRole(user.getId(),user.getRole());
        if(!user.getRole().equals(BUSINESS)){
            throw  new UserException(UserErrorCode.NOT_HAVE_PERMISSION);
        }
        ProductType productType = ProductType.HOTEL;

        Product product = new Product().builder().name(productRequestDto.getName())
                .star(productRequestDto.getStar())
                .score(productRequestDto.getScore())
                .address(productRequestDto.getAddress())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .productType(productType)
                .ownerComment(productRequestDto.getOwnerComment())
                .user(user)
                .imageUrl(imageUrl)
                .build();
        productRepository.save(product);
        return new ProductResponseDto(product);

    }
    //프로덕트 전체 조회
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> result = new ArrayList<>();
        for (Product product : products) {
            result.add(new ProductResponseDto(product));
        }
        return result;
    }
    //프로덕트 수정
    @Transactional
    public String modifyProduct(User user,ProductRequestDto productRequestDto,Long productId) {
        user = userRepository.findById(user.getId()).orElseThrow(
                ()->new UserException(UserErrorCode.NOT_AUTHOR)
        );
        if(!user.getRole().equals(BUSINESS)){
            throw  new UserException(UserErrorCode.NOT_HAVE_PERMISSION);
        }
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ProductException(ProductErrorCode.LODGING_NOT_FOUND)
        );
        if(isMatchProduct(product,user)){product.update(productRequestDto);}
        return "숙소 수정 성공";

    }
    //프로덕트 삭제
    @Transactional
    public String deleteProduct(User user,Long productId) {
        user = userRepository.findById(user.getId()).orElseThrow(
                ()->new UserException(UserErrorCode.NOT_AUTHOR)
        );
        if(!user.getRole().equals(BUSINESS)){
            throw  new UserException(UserErrorCode.NOT_HAVE_PERMISSION);
        }
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ProductException(ProductErrorCode.LODGING_NOT_FOUND)
        );
        if(isMatchProduct(product,user)){productRepository.deleteById(product.getId());}

        return "숙소 삭제 성공";
    }
    //프로덕트 상세 조회
    @Transactional(readOnly = true)
    public Product getProduct(Long productId) {
         Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ProductException(ProductErrorCode.LODGING_NOT_FOUND)
        );
        return product;
    }
    //프로덕트와 사용자 일치 확인
    private boolean isMatchProduct(Product product, User user) {
        return product.getUser().getId().equals(user.getId());
    }
}
