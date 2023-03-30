package com.example.hamgaja.users.controller;

import com.example.hamgaja.users.dto.SignupRequestDto;
import com.example.hamgaja.users.entity.User;
import com.example.hamgaja.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init () {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @DisplayName("회원 가입 성공")
    @Test
    void signUpSuccess() throws Exception {
//        SignupRequestDto signUpRequestDto = SignupRequestDto.builder()
//                    .email("test@test")
//                    .username("장한진")
//                    .password("1234qwer")
//                    .build();
        }
        // given

        User user = new User();

    }