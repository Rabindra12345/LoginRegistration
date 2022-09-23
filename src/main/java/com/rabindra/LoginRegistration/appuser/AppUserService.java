package com.rabindra.LoginRegistration.appuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

//	@Autowired
	
	private final String USER_NOT_FOUND_MSG = "User with username %s not found.";
	private final AppUserRepository appUserRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return appUserRepository.findByEmail(email).orElseThrow(()->
				new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}
	
	public String signUp(AppUser appUser) {
		
		boolean userExist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
		
		if(userExist) {
			throw new IllegalStateException("EMAIL ALREADY TAKEN.");
		}
		
		String encodedPass= bCryptPasswordEncoder.encode(appUser.getPassword());
		
		appUser.setPassword(encodedPass);
		
		//TODO: send confirmation token
		
		return "it works";
	}

}
