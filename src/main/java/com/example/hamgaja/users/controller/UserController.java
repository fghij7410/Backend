package com.example.hamgaja.users.controller;

import com.example.hamgaja.jwt.JwtUtil;
import com.example.hamgaja.users.dto.LoginRequestDto;
import com.example.hamgaja.users.dto.SignupRequestDto;
import com.example.hamgaja.users.dto.TermsRequestDto;
import com.example.hamgaja.users.entity.User;
import com.example.hamgaja.users.exception.UserErrorCode;
import com.example.hamgaja.users.exception.UserException;
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

    //유저 회원가입
    @PostMapping("/users/signup/user")
    public ResponseEntity signupUser(@Valid @RequestBody SignupRequestDto signupRequestDto, TermsRequestDto termsRequestDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new UserException(UserErrorCode.NOT_PROPER_INPUTFORM);
        }
        return ResponseMessage.SuccessResponse(userService.signupUser(signupRequestDto, termsRequestDto) , "");
    }
    //사업자 회원가입
    @PostMapping("/users/signup/auth")
    public ResponseEntity signupAuth(@Valid @RequestBody SignupRequestDto signupRequestDto, TermsRequestDto termsRequestDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new UserException(UserErrorCode.NOT_PROPER_INPUTFORM);
        }
        return ResponseMessage.SuccessResponse(userService.signupAuth(signupRequestDto, termsRequestDto) , "");
    }

    // 로그인 토큰발급(이메일, 닉네임)
    @PostMapping("/users/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // 토큰발급을 위해 user를 반환.
        User user = userService.login(loginRequestDto);

        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getEmail(), user.getRole()));
        return ResponseMessage.SuccessResponse("로그인 성공!", "");
    }

    // 중복된 닉네임 검사
    @PostMapping("/users/check/username")
    public ResponseEntity checkEmail(@RequestBody HashMap<String, String> username) {
        return ResponseMessage.SuccessResponse(userService.checkUsername(username.get("username")), "");
    }
}
