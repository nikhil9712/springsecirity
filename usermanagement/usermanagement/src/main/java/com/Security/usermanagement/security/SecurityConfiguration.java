package com.Security.usermanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private JWTFilter jwtFilter;

	@Bean
	public SecurityFilterChain filterChain1(HttpSecurity config) throws Exception {
		return config.csrf(csrf -> csrf.disable())
				// Adding stateless skipped my login api JWT filte how need to learn
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
						request -> request.requestMatchers("/login").permitAll().requestMatchers("/signUp").permitAll()
								.requestMatchers("/getEmployees*").permitAll().anyRequest().authenticated())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.formLogin(form -> form.disable()).build();
	}

}
