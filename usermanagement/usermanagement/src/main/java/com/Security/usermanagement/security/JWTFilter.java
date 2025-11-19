package com.Security.usermanagement.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		filterChain.doFilter(request, response);
	}
	private static final String[] PERMITTED_PATTERNS = {
	        "/login",
	        "/register",
	        "/health",
	        "/actuator/**",
	        "/public/**"
	};	

	/*
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		List<RequestMatcher> matchers = new ArrayList<>();
		for (String pattern : PERMITTED_PATTERNS) {
			matchers.add(new AntPathRequestMatcher(pattern));
		}
		RequestMatcher excludeFromFilterRequestMatcher = new OrRequestMatcher(matchers);
		return excludeFromFilterRequestMatcher.matches(request);
	}
	*/
}
