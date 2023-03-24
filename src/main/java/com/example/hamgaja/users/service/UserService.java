package com.example.hamgaja.users.service;

import com.example.hamgaja.users.dto.LoginRequestDto;
import com.example.hamgaja.users.dto.SignupRequestDto;
import com.example.hamgaja.users.entity.Terms;
import com.example.hamgaja.users.entity.User;
import com.example.hamgaja.users.entity.UserRoleEnum;
import com.example.hamgaja.users.exception.CustomErrorCode;
import com.example.hamgaja.users.exception.CustomException;
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
    private final PasswordEncoder passwordEncoder;
//    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    @Transactional
    public String signup(SignupRequestDto signupRequestDto) {
        // 회원아이디 중복 확인
        boolean found = userRepository.findByEmail(signupRequestDto.getEmail()).isPresent();
        if (found) {
            throw new CustomException(CustomErrorCode.DUPLICATE_USER);
        }

        //닉네임 중복 확인
        found = userRepository.findByUsername(signupRequestDto.getUsername()).isPresent();
        if (found) {
            throw new CustomException(CustomErrorCode.DUPLICATE_NICKNAME);
        }

        // 권한 부여 (사업자등록번호가 null이면 USER)
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.getBusiness_number() != null) {
            role = UserRoleEnum.BUSINESS;
        }

        Terms terms = new Terms();  //약관

        User user = new User(signupRequestDto, passwordEncoder.encode(signupRequestDto.getPassword()), terms );
        userRepository.save(user);

        return "회원가입 성공";
    }

//    @Transactional(readOnly = true)
//    public Map<String, String> login(LoginRequestDto loginRequestDto) {
//        // 사용자 확인
//        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(
//                () -> new CustomException(CustomErrorCode.USER_NOT_FOUND)
//        );
//        // 비밀번호 확인
//        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
//            throw  new CustomException(CustomErrorCode.NOT_PROPER_PASSWORD);
//        }
//
//        Map<String, String> result = new HashMap<>();
//        result.put("username", user.getUsername());
//        result.put("nickname", user.getNickname());
//
//        return result;
//    }

//    @Transactional
//    public String checkUsername(String username) {
//        if (userRepository.existsUserByUsername(username)) {
//            throw new CustomException(CustomErrorCode.DUPLICATE_USER);
//        }
//        return "사용가능한 아이디입니다.";
//    }
}
