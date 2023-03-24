package com.example.hamgaja.users.repository;

import com.example.hamgaja.users.entity.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Terms, Long> {
}
