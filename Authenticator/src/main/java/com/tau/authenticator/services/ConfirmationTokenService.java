package com.tau.authenticator.services;

import java.time.LocalDateTime;
import java.util.Optional;

import com.tau.authenticator.models.ConfirmationToken;
import com.tau.authenticator.repositories.ConfirmationTokenRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;


    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token); 
    }


    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }


    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
