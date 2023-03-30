package com.example.hamgaja.users.entity;

import com.example.hamgaja.users.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToOne
    @JoinColumn(name = "terms_id")
    private Terms terms;



    public User(SignupRequestDto signupRequestDto, String password, UserRoleEnum role, Terms terms) {
        this.email = signupRequestDto.getEmail();
        this.password = password;   //서비스로직에서 패스워드인코더 사용
        this.username = signupRequestDto.getUsername();
        this.role = role;
        this.terms = terms;
    }
}
