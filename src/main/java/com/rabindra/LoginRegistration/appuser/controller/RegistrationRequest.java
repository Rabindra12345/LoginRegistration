package com.rabindra.LoginRegistration.appuser.controller;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
//@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class RegistrationRequest {

	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
}
