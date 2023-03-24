package com.example.hamgaja.users.entity;

import com.example.hamgaja.users.dto.TermsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Terms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean privacy_term;

    @Column(nullable = false)
    private boolean marketing_term;

    @Column(nullable = false)
    private boolean gps_term;

    public Terms(TermsRequestDto requestDto) {
        this.privacy_term = requestDto.isPrivacy_term();
        this.marketing_term = requestDto.isMarketing_term();
        this.gps_term = requestDto.isGps_term();
    }
}
