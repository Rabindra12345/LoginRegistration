package com.rabindra.LoginRegistration.appuser.regis.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

	
	private final ConfirmationTokenRepository confirmationTokenRepository;
	
	public void saveToken(ConfirmationToken token) {
		
		confirmationTokenRepository.save(token);
	}
	
	public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.getByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
