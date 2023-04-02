package com.example.hamgaja.products.service;


import com.example.hamgaja.products.dto.*;
import com.example.hamgaja.products.entity.*;
import com.example.hamgaja.products.exception.ProductErrorCode;
import com.example.hamgaja.products.exception.ProductException;
import com.example.hamgaja.products.repository.*;
import com.example.hamgaja.security.UserDetailsImpl;
import com.example.hamgaja.users.entity.User;
import com.example.hamgaja.users.exception.UserErrorCode;
import com.example.hamgaja.users.exception.UserException;
import com.example.hamgaja.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;


import static com.example.hamgaja.users.entity.UserRoleEnum.BUSINESS;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final RoomRepository roomRepository;
    private final S3UploaderService s3UploaderService;
    private final CategoryRepository categoryRepository;
    private final ProductCategoryRepository productCategoryRepository;

    private final S3ImageRepository s3ImageRepository;

    //프로덕트 추가
    @Transactional
    public ProductResponseDto addProduct(UserDetailsImpl userDetails,
                                         List<S3ResponseDto> s3MainImage,
                                         List<S3ResponseDto> s3RoomImage,
                                         ProductRequestDto productRequestDto) {
        User user = userRepository.findByIdAndRole(userDetails.getUser().getId(), userDetails.getUser().getRole());
        if (!user.getRole().equals(BUSINESS)) {
            throw new UserException(UserErrorCode.NOT_HAVE_PERMISSION);
        }
        ProductType productType = ProductType.HOTEL;
        Location location = new Location("서울특별시 강남구", "서울특별시", "강남구");
        locationRepository.save(location);
        Map<String, Boolean> categoryMap = productRequestDto.getCategory();

        Product product = Product.builder().name(productRequestDto.getName())
                .star(productRequestDto.getStar())
                .address(productRequestDto.getAddress())
                .description(productRequestDto.getDescription())
                .productType(productType)
                .ownerComment(productRequestDto.getOwnerComment())
                .user(user)
                .imageUrl(s3MainImage.get(0).getUploadFileUrl())
                .location(location)
                .build();

        productRepository.save(product);

        Set<Map.Entry<String, Boolean>> entries = categoryMap.entrySet();
        List<ProductCategory> productCategories = new ArrayList<>();
        for (Map.Entry<String, Boolean> next : entries) {
            if (next.getValue()) {
                Category category = categoryRepository.findByName(next.getKey())
                        .orElseThrow(() -> new ProductException(ProductErrorCode.NOT_PROPER_CATEGORY));
                productCategories.add(new ProductCategory(product, category));
            }
        }

        productCategoryRepository.saveAll(productCategories);

        List<Room> roomList = new ArrayList<>();
        for (int i = 0; i < s3RoomImage.size(); i++) {
            roomList.add(new Room(productRequestDto.getRoomList().get(i), s3RoomImage.get(i).getUploadFileUrl(), product));
        }

        roomRepository.saveAll(roomList);
        Long minPrice = roomList.stream().mapToLong(Room::getRoomPrice).min().orElse(0L);
        return new ProductResponseDto(product, categoryMap, minPrice);
    }

    //프로덕트 전체 조회
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> result = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Product product : products) {
            // 숙소에 해당하는 카테고리들을 리스트로 받기
            List<Category> productCategories = productCategoryRepository.findAllByProductId(product.getId())
                    .stream().map(ProductCategory::getCategory).toList();

            // 숙소의 카테고리와 비즈니스에서 관리하는 카테고리를 매칭해서 숙소 카테고리가 포함하고 있으면 True, 아니면 False
            Map<String, Boolean> category = categories.stream()
                    .collect(Collectors.toMap(Category::getName,
                            s -> productCategories.contains(s) ? Boolean.TRUE :Boolean.FALSE));

            // 숙소의 객실 리스트의 stream 이용하여 min 반환
            Long minPrice = product.getRooms().stream().mapToLong(Room::getRoomPrice).min().orElse(0L);
            result.add(new ProductResponseDto(product, category, minPrice));
        }
        return result;
    }

    //프로덕트 수정
    @Transactional
    public String modifyProduct(UserDetailsImpl userDetails, ProductRequestDto productRequestDto, Long productId, String fileType, List<MultipartFile> multipartFiles) {
        User user = userRepository.findByIdAndRole(userDetails.getUser().getId(), userDetails.getUser().getRole());
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductException(ProductErrorCode.LODGING_NOT_FOUND)
        );


        if (!isMatchProduct(product, user)) {
            throw new UserException(UserErrorCode.NOT_AUTHOR);
        } else {
            s3UploaderService.deleteFile(productId);
            s3UploaderService.uploadFiles(fileType, multipartFiles);
            product.update(productRequestDto);
        }
        return "숙소 수정 성공";

    }

    //프로덕트 삭제
    @Transactional
    public String deleteProduct(UserDetailsImpl userDetails, Long productId) {
        User user = userRepository.findByIdAndRole(userDetails.getUser().getId(), userDetails.getUser().getRole());

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductException(ProductErrorCode.LODGING_NOT_FOUND)
        );
        if (!isMatchProduct(product, user)) {
            throw new UserException(UserErrorCode.NOT_AUTHOR);

        }else{
            List<Room> roomImageUrl= roomRepository.findByRoomImageUrl(productId);//파일 URL찾기
            //s3ImageRepository.deleteAllByUploadFileUrl(roomImageUrl);
            roomRepository.deleteAllByProductId(productId);
            s3UploaderService.deleteFile(productId);
            productRepository.deleteById(product.getId());

        }

        return "숙소 삭제 성공";
    }

    //프로덕트 상세 조회
    @Transactional(readOnly = true)
    public SingleProductResponseDto getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductException(ProductErrorCode.LODGING_NOT_FOUND)
        );
        List<RoomResponseDto> roomList = roomRepository.findAllByProductId(product.getId())
                .stream().map(RoomResponseDto::new).toList();
        return new SingleProductResponseDto(product, roomList);
    }

    //프로덕트와 사용자 일치 확인
    private boolean isMatchProduct(Product product, User user) {
        return product.getUser().getId().equals(user.getId());
    }
}
