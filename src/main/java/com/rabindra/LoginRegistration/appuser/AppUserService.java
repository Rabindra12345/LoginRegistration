package com.rabindra.LoginRegistration.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rabindra.LoginRegistration.appuser.regis.token.ConfirmationToken;
import com.rabindra.LoginRegistration.appuser.regis.token.ConfirmationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

//	@Autowired
	
	private final String USER_NOT_FOUND_MSG = "User with username %s not found.";
	private final AppUserRepository appUserRepository;
	
	private final ConfirmationTokenRepository confirmationTokenRepository;
	
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
		
		appUserRepository.save(appUser);
		
		//TODO: send confirmation token
		
		//Generate UUID
		
		String token = UUID.randomUUID().toString();
		
		ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				appUser
		);
		
		//save the confirmation token
		confirmationTokenRepository.save(confirmationToken);
		
		//Generate email link
		
		
		return token;
	}
	
	 public int enableAppUser(String email) {
	        return appUserRepository.enableAppUser(email);
	    }

}
