package com.example.hamgaja.users.service;

import com.example.hamgaja.security.UserDetailsImpl;
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

import static com.example.hamgaja.users.entity.UserRoleEnum.BUSINESS;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TermRepository termRepository;
    private final PasswordEncoder passwordEncoder;

    //유저 회원가입
    @Transactional
    public String signupUser(SignupRequestDto signupRequestDto, TermsRequestDto termsRequestDto) {
        // 회원아이디(이메일) 중복 확인
        checkedEmailAndUserName(signupRequestDto);
        // 권한 부여
        UserRoleEnum role = UserRoleEnum.USER;
        // 약관 처리
        Terms terms = termRepository.save(new Terms(termsRequestDto));

        User user = new User(signupRequestDto, passwordEncoder.encode(signupRequestDto.getPassword()), role, terms );
        userRepository.save(user);

        return "회원가입 성공";
    }

    //사업자 회원가입
    @Transactional
    public String signupAuth(SignupRequestDto signupRequestDto, TermsRequestDto termsRequestDto) {
        // 회원아이디(이메일) 중복 확인
        checkedEmailAndUserName(signupRequestDto);
        // 권한 부여
        UserRoleEnum role = UserRoleEnum.BUSINESS;
        // 약관 처리
        Terms terms = termRepository.save(new Terms(termsRequestDto));

        User user = new User(signupRequestDto, passwordEncoder.encode(signupRequestDto.getPassword()), role, terms );
        userRepository.save(user);

        return "사업자 가입 성공";
    }

    @Transactional(readOnly = true)
    public User login(LoginRequestDto loginRequestDto) {
        // 사용자 확인
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND)
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw  new UserException(UserErrorCode.NOT_PROPER_PASSWORD);
        }

        return user;
    }

    @Transactional
    public String checkUsername(String username) {
        if (userRepository.existsUserByUsername(username)) {
            throw new UserException(UserErrorCode.DUPLICATE_NICKNAME);
        }
        return "사용가능한 닉네임입니다.";
    }

    //회원가입을 위한 검사
    private void checkedEmailAndUserName (SignupRequestDto signupRequestDto) {
        // 회원아이디(이메일) 중복 확인
        boolean found = userRepository.findByEmail(signupRequestDto.getEmail()).isPresent();
        if (found) {
            throw new UserException(UserErrorCode.DUPLICATE_USER);
        }
        //포스트맨 닉네임 중복확인을 위한 로직
        found = userRepository.findByUsername(signupRequestDto.getUsername()).isPresent();
        if (found) {
            throw new UserException(UserErrorCode.DUPLICATE_NICKNAME);
        }
    }

    public User checkBUSINESS(UserDetailsImpl userDetails){
       User user= userDetails.getUser();
        if(!user.getRole().equals(BUSINESS)){
            throw  new UserException(UserErrorCode.NOT_HAVE_PERMISSION);
        }
        return user;
    }


}
