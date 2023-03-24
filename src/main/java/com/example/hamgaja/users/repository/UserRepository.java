package com.example.hamgaja.users.repository;

import com.example.hamgaja.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername (String username);
    Optional<User> findByEmail (String email);
    boolean existsUserByUsername (String username);
}
