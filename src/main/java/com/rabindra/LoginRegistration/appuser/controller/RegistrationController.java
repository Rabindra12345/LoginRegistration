package com.rabindra.LoginRegistration.appuser.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/registration")
@AllArgsConstructor
public class RegistrationController {

	
	private RegistrationService registrationService;
	
	@PostMapping
	public String registration(@RequestBody RegistrationRequest request) {
		
		return registrationService.registration(request);
	}
}
