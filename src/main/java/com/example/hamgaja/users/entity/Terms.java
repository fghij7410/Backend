package com.example.hamgaja.users.entity;

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
    private String privacy_therm;

    @Column(nullable = false)
    private String markething_therm;

    @Column(nullable = false)
    private String gps_therm;
}
