package com.example.hamgaja.deploy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public ResponseEntity getHealthCheck() {
        return ResponseEntity.ok("8080 health check ok");
    }
}
