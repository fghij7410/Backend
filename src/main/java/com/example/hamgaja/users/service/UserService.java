package com.example.hamgaja.users.service;

import com.example.hamgaja.users.dto.LoginRequestDto;
import com.example.hamgaja.users.dto.SignupRequestDto;
import com.example.hamgaja.users.dto.TermsRequestDto;
import com.example.hamgaja.users.entity.Terms;
import com.example.hamgaja.users.entity.User;
import com.example.hamgaja.users.entity.UserRoleEnum;
import com.example.hamgaja.users.exception.UserErrorCode;
import com.example.hamgaja.users.exception.UserException;
import com.example.hamgaja.users.repository.TermRepository;
import com.example.hamgaja.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TermRepository termRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String signup(SignupRequestDto signupRequestDto, TermsRequestDto termsRequestDto) {
        // 회원아이디 중복 확인
        boolean found = userRepository.findByEmail(signupRequestDto.getEmail()).isPresent();
        if (found) {
            throw new UserException(UserErrorCode.DUPLICATE_USER);
        }

        //닉네임 중복 확인 추후 제거예정
        found = userRepository.findByUsername(signupRequestDto.getUsername()).isPresent();
        if (found) {
            throw new UserException(UserErrorCode.DUPLICATE_NICKNAME);
        }

        // 권한 부여
        UserRoleEnum role = UserRoleEnum.USER;

        // 약관 처리
        Terms terms = termRepository.save(new Terms(termsRequestDto));

        //
        User user = new User(signupRequestDto, passwordEncoder.encode(signupRequestDto.getPassword()), role, terms );
        userRepository.save(user);

        return "회원가입 성공";
    }

    @Transactional(readOnly = true)
    public Map<String, String> login(LoginRequestDto loginRequestDto) {
        // 사용자 확인
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND)
        );
        // 비밀번호 확인
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw  new UserException(UserErrorCode.NOT_PROPER_PASSWORD);
        }

        Map<String, String> result = new HashMap<>();
        result.put("username", user.getUsername());
        result.put("email", user.getEmail());

        return result;
    }

    @Transactional
    public String checkUsername(String username) {
        if (userRepository.existsUserByUsername(username)) {
            throw new UserException(UserErrorCode.DUPLICATE_USER);
        }
        return "사용가능한 닉네임입니다.";
    }
}
