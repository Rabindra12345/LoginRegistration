package com.rabindra.LoginRegistration.appuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

//	@Autowired
	
	private final String USER_NOT_FOUND_MSG = "User with username %s not found.";
	private final AppUserRepository appUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return appUserRepository.findByEmail(email).orElseThrow(()->
				new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}

}
