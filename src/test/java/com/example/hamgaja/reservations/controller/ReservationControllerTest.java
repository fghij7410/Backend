package com.example.hamgaja.reservations.controller;

import com.example.hamgaja.reservations.dto.ReservationRequestDto;
import com.example.hamgaja.reservations.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {
    @InjectMocks
    private ReservationController reservationController;

    @Mock
    private ReservationService reservationService;

    /**
     * mockMvc : 컨트롤러 테스트는 HTTP 요청 응답 등 web MVC 동작 재현 필요함
     */
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();

    }

    @DisplayName("post_요청의_체크인_체크아웃_시간이_변환된다")
    @Test
    public void datetimeformatParse() throws Exception {
        // given
        String url = "/rooms/1/reservations";

        // when
        ResultActions resultActions = this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"checkIn\":\"2023-03-28T11:00\", \"checkOut\":\"2023-03-29T12:00\"}"));
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());

        // then
        resultActions
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("예약 등록 성공")
    @Test
    void reserveSuccess() throws Exception {
        // given

        // when

        // then
    }

    private ReservationRequestDto reserveRequest() {
        return null;
    }
}