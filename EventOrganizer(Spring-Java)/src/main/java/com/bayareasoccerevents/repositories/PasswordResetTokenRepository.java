package com.bayareasoccerevents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bayareasoccerevents.models.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);
}
