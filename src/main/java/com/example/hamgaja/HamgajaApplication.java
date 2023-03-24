package com.example.hamgaja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HamgajaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HamgajaApplication.class, args);
    }

}
