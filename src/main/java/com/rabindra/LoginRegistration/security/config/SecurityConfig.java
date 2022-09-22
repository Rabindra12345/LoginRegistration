package com.rabindra.LoginRegistration.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rabindra.LoginRegistration.appuser.AppUserService;
//import com.rabindra.bookmarket.model.Role;
//import com.rabindra.bookmarket.security.jwt.JwtAuthorizationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig  {
	
	private final AppUserService appUserService;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//	http.
//		csrf().disable()
//		.authorizeRequests()
//			.antMatchers("/api/registration/**")
//			.permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.formLogin();
//	}
	
	 @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.
			csrf().disable()
			.authorizeRequests()
				.antMatchers("/api/registration/**")
				.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin();
	    
	    http.authenticationProvider(daoAuthenticationProvider());
	      
	    return http.build();
	  }

//	 @Override
//	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.authenticationProvider(daoAuthenticationProvider());
//	    }

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(appUserService);
		
		return provider;
	}
	

	
	
}
