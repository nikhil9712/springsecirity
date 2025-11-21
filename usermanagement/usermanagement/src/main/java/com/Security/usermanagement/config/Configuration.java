package com.Security.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@org.springframework.context.annotation.Configuration
public class Configuration {

	//@Bean
	public UserDetailsService detailsServices() {
		UserDetails user1 = User.withUsername("Nikhil").password(passwordEncoder().encode("Nikhil@123")).roles("ADMIN").build();
		UserDetails user2 = User.withUsername("Sarang").password(passwordEncoder().encode("Sarang@123")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user1, user2);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
