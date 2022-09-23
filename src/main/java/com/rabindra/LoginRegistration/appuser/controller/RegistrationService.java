package com.rabindra.LoginRegistration.appuser.controller;

import org.springframework.stereotype.Service;

import com.rabindra.LoginRegistration.appuser.AppUser;
import com.rabindra.LoginRegistration.appuser.AppUserService;
import com.rabindra.LoginRegistration.appuser.Role;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

	private final EmailValidator emailValidator;
	
	private final AppUserService appUserService;
	
	public String registration(RegistrationRequest request) {
		
		boolean isEmailValid = emailValidator.test(request.getEmail());
		
		if(!isEmailValid) {
			throw new IllegalStateException("EMAIL IS NOT VALID");
		}
		
		return appUserService
				.signUp( new AppUser(
						request.getFirstName(),
						request.getLastName(),
						request.getEmail(),
						request.getPassword(),
						Role.USER
						));
	}

}
