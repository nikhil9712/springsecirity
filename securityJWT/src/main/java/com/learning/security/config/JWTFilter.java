package com.learning.security.config;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learning.security.service.JWTService;
import com.learning.security.service.MyUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	ApplicationContext context;

	@Autowired
	JWTService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		
		String path = request.getServletPath();

		// Skip JWT validation for public endpoints
		if (path.equals("/login") || path.equals("/registerUser")) {
			filterChain.doFilter(request, response);
			return;
		}
		    
		// Bearer
		// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYW5rZXQiLCJpYXQiOjE3NTE3NDA0NDgsImV4cCI6MTc1MTc0MDU1Nn0.aLmGvqx3Gj6J0l4hP2K2HS2sFpKbrS0fRL6iQFFNRWE
		String header = request.getHeader("Authorization");
		String token = null;

		// Getting the user name from request
		if (Objects.nonNull(header) && header.startsWith("Bearer")) {
			token = header.substring(7);
		}
		
		String userName = jwtService.extractUsernameFromToken(token);

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = context.getBean(MyUserDetailService.class).loadUserByUsername(userName);

			if (jwtService.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}

		filterChain.doFilter(request, response);

	}

}
