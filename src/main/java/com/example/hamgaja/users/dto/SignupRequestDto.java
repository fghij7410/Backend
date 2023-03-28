package com.example.hamgaja.users.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class SignupRequestDto {
    @NotBlank
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    // ^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$ 최종배포 테스트 때 변경. 도메인이 있는 이메일에 대한 정규식(ex. google)
    private String email;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9]{7,14}$",
    message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,6}$",
    message = "닉네임 형식이 올바르지 않습니다.")
    private String username;

//    private String businessNumber;

}
