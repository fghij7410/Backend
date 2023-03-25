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
    private boolean privacyTerm;

    @Column(nullable = false)
    private boolean marketingTerm;

    @Column(nullable = false)
    private boolean gpsTerm;

    public Terms(TermsRequestDto requestDto) {
        this.privacyTerm = requestDto.isPrivacyTerm();
        this.marketingTerm = requestDto.isMarketingTerm();
        this.gpsTerm = requestDto.isGpsTerm();
    }
}
