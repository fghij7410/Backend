package com.example.hamgaja.products.service;

import com.example.hamgaja.products.dto.ProductRequestDto;
import com.example.hamgaja.products.dto.ProductResponseDto;
import com.example.hamgaja.products.entity.Location;
import com.example.hamgaja.products.entity.Product;
import com.example.hamgaja.products.entity.ProductType;
import com.example.hamgaja.products.exception.ProductErrorCode;
import com.example.hamgaja.products.exception.ProductException;
import com.example.hamgaja.products.repository.LocationRepository;
import com.example.hamgaja.products.repository.ProductRepository;
import com.example.hamgaja.security.UserDetailsImpl;
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

    private final LocationRepository locationRepository;
    //프로덕트 추가
    @Transactional
    public ProductResponseDto addProduct(UserDetailsImpl userDetails, String imageUrl , ProductRequestDto productRequestDto) {
      User  user = userRepository.findByIdAndRole(userDetails.getUser().getId(), userDetails.getUser().getRole());
        if(!user.getRole().equals(BUSINESS)){
            throw  new UserException(UserErrorCode.NOT_HAVE_PERMISSION);
        }
        ProductType productType = ProductType.HOTEL;
        Location location = new Location("서울특별시 강남구","서울특별시","강남구");
        locationRepository.save(location);

        Product product = new Product().builder().name(productRequestDto.getName())
                .star(productRequestDto.getStar())
                .address(productRequestDto.getAddress())
                .description(productRequestDto.getDescription())
                .productType(productType)
                .ownerComment(productRequestDto.getOwnerComment())
                .user(user)
                .imageUrl(imageUrl)
                .location(location)
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
    public String modifyProduct(UserDetailsImpl userDetails,ProductRequestDto productRequestDto,Long productId) {
      User  user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
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
    public String deleteProduct(UserDetailsImpl userDetails,Long productId) {
      User  user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
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
