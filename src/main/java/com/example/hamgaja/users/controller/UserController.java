package com.example.hamgaja.users.controller;

import com.example.hamgaja.jwt.JwtUtil;
import com.example.hamgaja.users.dto.LoginRequestDto;
import com.example.hamgaja.users.dto.SignupRequestDto;
import com.example.hamgaja.users.dto.TermsRequestDto;
import com.example.hamgaja.users.exception.CustomErrorCode;
import com.example.hamgaja.users.exception.CustomException;
import com.example.hamgaja.message.ResponseMessage;
import com.example.hamgaja.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    //회원가입
    @PostMapping("/users/signup")
    public ResponseEntity signup(@Valid @RequestBody SignupRequestDto signupRequestDto, TermsRequestDto termsRequestDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(CustomErrorCode.NOT_PROPER_INPUTFORM);
        }
        return ResponseMessage.SuccessResponse(userService.signup(signupRequestDto, termsRequestDto) , "");
    }

    // 로그인 토큰발급(이메일, 닉네임)
    @PostMapping("/users/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        Map<String, String> user = userService.login(loginRequestDto);
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.get("username"), user.get("email")));
        return ResponseMessage.SuccessResponse("로그인 성공!", "");
    }

    // 중복된 닉네임 검사
    @PostMapping("/users/check/username")
    public ResponseEntity checkEmail(@RequestBody HashMap<String, String> username) {
        return ResponseMessage.SuccessResponse(userService.checkUsername(username.get("username")), "");
    }
}
