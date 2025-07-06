package com.learning.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Don't use default config use my Configuration
public class SecurityConfig {


	@Autowired
	UserDetailsService detailsService;
	
	@Autowired
	JWTFilter jwtFilter;
	
	/*
	
	User Request
	   ↓
	UsernamePasswordAuthenticationFilter
	   ↓
	AuthenticationManager
	   ↓
	[AuthenticationProvider1, AuthenticationProvider2, ...]
	   ↓
	Authenticated Object → SecurityContext

*/
 
	// Level 1 We Have Added A Security Filter Chain And Added Basic configurations
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(request ->
				request.requestMatchers("registerUser","login")
				.permitAll()
				.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
				.build();
	}
 
	/*
	 * Level 2 : We have Added our own UserDetailsService and configured accordingly
	 * But user are not from database we have used User Class of spring security to
	 * create username , password and it will apply according and level 1 steps will
	 * not be applicable We Can Not Use Level 2 because it used hardcoded user and
	 * we should fetch user from database
	

	//@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.withDefaultPasswordEncoder().username("Nikhil").password("N@123").roles("ADMIN")
				.build();

		UserDetails user2 = User.withDefaultPasswordEncoder().username("Sanket").password("S@123").roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user1, user2);
	}
	 */

	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration  config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	/*
	 * 1> By Default Browser send a object i.e. Authentication object which is by
	 * default unauthenticated 2> We Can to create Authentication Provider which
	 * will make unauthenticated Authentication object to authenticated
	 * Authentication object 3> Authenticated Authentication object can be send for
	 * further process (API CALL )
	 */

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		//authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		authenticationProvider.setUserDetailsService(detailsService);
		return authenticationProvider;
	}
}
