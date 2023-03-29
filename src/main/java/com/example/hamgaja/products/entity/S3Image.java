package com.example.hamgaja.products.entity;

import com.example.hamgaja.products.dto.S3RequestDto;
import com.example.hamgaja.products.dto.S3ResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class S3Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalFileName;
    private String uploadFileName;
    private String uploadFilePath;
    private String uploadFileUrl;

    public S3Image(S3ResponseDto s3ResponseDto) {
        this.originalFileName = s3ResponseDto.getOriginalFileName();
        this.uploadFileName = s3ResponseDto.getUploadFileName();
        this.uploadFilePath = s3ResponseDto.getUploadFilePath();
        this.uploadFileUrl = s3ResponseDto.getUploadFileUrl();

    }


    public S3Image(S3RequestDto s3RequestDto) {
        this.originalFileName = s3RequestDto.getOriginalFileName();
        this.uploadFileName = s3RequestDto.getUploadFileName();
        this.uploadFilePath = s3RequestDto.getUploadFilePath();
        this.uploadFileUrl = s3RequestDto.getUploadFileUrl();
    }
}