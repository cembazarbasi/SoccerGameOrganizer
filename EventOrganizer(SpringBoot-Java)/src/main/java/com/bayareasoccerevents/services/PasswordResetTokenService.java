package com.bayareasoccerevents.services;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bayareasoccerevents.models.PasswordResetToken;
import com.bayareasoccerevents.models.User;
import com.bayareasoccerevents.repositories.PasswordResetTokenRepository;



@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    public PasswordResetToken createToken(User user, String token) {
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        
        resetToken.setExpiryDate(Instant.now().plus(Duration.ofHours(1)));
        return tokenRepository.save(resetToken);
    }

    public PasswordResetToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void deleteToken(PasswordResetToken token) {
        tokenRepository.delete(token);
    }
}
