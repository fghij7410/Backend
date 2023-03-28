package com.example.hamgaja.products.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.example.hamgaja.products.dto.S3RequestDto;
import com.example.hamgaja.products.dto.S3ResponseDto;
import com.example.hamgaja.products.entity.S3Image;
import com.example.hamgaja.products.exception.ProductErrorCode;
import com.example.hamgaja.products.exception.ProductException;
import com.example.hamgaja.products.repository.S3ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3UploaderService {

    @Value("${spring.s3.bucket}")
    private String bucketName;

    private final AmazonS3Client amazonS3Client;
    private final S3ImageRepository s3ImageRepository;

    /**
     * S3로 파일 업로드
     */
    public S3ResponseDto uploadFiles(String fileType, List<MultipartFile> multipartFiles) {

        List<S3RequestDto> s3files = new ArrayList<>();

        String uploadFilePath = fileType + "/" + getFolderName();

        for (MultipartFile multipartFile : multipartFiles) {

            String originalFileName = multipartFile.getOriginalFilename();
            String uploadFileName = getUuidFileName(originalFileName);
            String uploadFileUrl = "";

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(multipartFile.getSize());
            objectMetadata.setContentType(multipartFile.getContentType());

            try (InputStream inputStream = multipartFile.getInputStream()) {

                String keyName = uploadFilePath + "/" + uploadFileName; // ex) 구분/년/월/일/파일.확장자

                // S3에 폴더 및 파일 업로드
                amazonS3Client.putObject(
                        new PutObjectRequest(bucketName, keyName, inputStream, objectMetadata));

                // TODO : 외부에 공개하는 파일인 경우 Public Read 권한을 추가, ACL 확인
                // S3에 업로드한 폴더 및 파일 URL
                uploadFileUrl = amazonS3Client.getUrl(bucketName, keyName).toString();


            } catch (IOException e) {
                e.printStackTrace();
                log.error("Filed upload failed", e);
            }
            s3files.add(
                    S3RequestDto.builder()
                            .originalFileName(originalFileName)
                            .uploadFileName(uploadFileName)
                            .uploadFilePath(uploadFilePath)
                            .uploadFileUrl(uploadFileUrl)
                            .build());
        }

//        List<S3Image> s3Images = s3files.stream().map(S3Image::new).toList();   //복수의 이미지 저장


        S3ResponseDto responseDto = new S3ResponseDto();
        for (S3RequestDto file : s3files) {
            responseDto.setOriginalFileName(file.getOriginalFileName());
            responseDto.setUploadFileName(file.getUploadFileName());
            responseDto.setUploadFilePath(file.getUploadFilePath());
            responseDto.setUploadFileUrl(file.getUploadFileUrl());
        }

        S3Image s3Image = new S3Image(responseDto);
        s3ImageRepository.save(s3Image);
        return responseDto;
    }


    /**
     * S3에 업로드된 파일 삭제
     */
    public void deleteFile(Long id) {
        S3Image s3Image = s3ImageRepository.findById(id).orElseThrow(
                () -> new ProductException(ProductErrorCode.NOT_FOUND_IMAGE));

        try {
            String keyName = s3Image.getUploadFilePath() + "/" + s3Image.getUploadFileName(); // ex) 구분/년/월/일/파일.확장자
            boolean isObjectExist = amazonS3Client.doesObjectExist(bucketName, keyName);
            if (isObjectExist) {
                amazonS3Client.deleteObject(bucketName, keyName);
            } else {
                throw new ProductException(ProductErrorCode.NOT_FOUND_IMAGE);
            }
        } catch (Exception e) {
            log.debug("Delete File failed", e);
        }

        s3ImageRepository.deleteById(id);
    }

//    public String deleteFile(String uploadFilePath, String uuidFileName) {
//
//        String result = "success";
//
//        try {
//            String keyName = uploadFilePath + "/" + uuidFileName; // ex) 구분/년/월/일/파일.확장자
//            boolean isObjectExist = amazonS3Client.doesObjectExist(bucketName, keyName);
//            if (isObjectExist) {
//                amazonS3Client.deleteObject(bucketName, keyName);
//            } else {
//                result = "file not found";
//            }
//        } catch (Exception e) {
//            log.debug("Delete File failed", e);
//        }
//
//        return result;
//    }

    /**
     * UUID 파일명 반환
     */
    public String getUuidFileName(String fileName) {
        String ext = fileName.substring(fileName.indexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + ext;
    }

    /**
     * 년/월/일 폴더명 반환
     */
    private String getFolderName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", "/");
    }
}