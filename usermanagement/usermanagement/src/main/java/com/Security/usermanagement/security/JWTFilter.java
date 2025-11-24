package com.Security.usermanagement.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.Security.usermanagement.entity.UserEntity;
import com.Security.usermanagement.repository.UserEntityRepository;
import com.Security.usermanagement.utils.JWTUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Autowired
	private HandlerExceptionResolver handlerExceptionResolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String requestTokenheader = request.getHeader("Authorization");
			if (requestTokenheader == null || !requestTokenheader.startsWith("Bearer")) {
				filterChain.doFilter(request, response);
				return;
			}

			String token = requestTokenheader.split("Bearer ")[1];
			String userName = jwtUtils.getUserNameFromToken(token);

			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserEntity userEntity = userEntityRepository.findByUserName(userName);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userEntity, null, userEntity.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			handlerExceptionResolver.resolveException(request, response, null, e);
		}
	}

	private static final String[] PERMITTED_PATTERNS = { "/login", "/register", "/health", "/actuator/**",
			"/public/**" };

	/*
	 * @Override protected boolean shouldNotFilter(HttpServletRequest request)
	 * throws ServletException { List<RequestMatcher> matchers = new ArrayList<>();
	 * for (String pattern : PERMITTED_PATTERNS) { matchers.add(new
	 * AntPathRequestMatcher(pattern)); } RequestMatcher
	 * excludeFromFilterRequestMatcher = new OrRequestMatcher(matchers); return
	 * excludeFromFilterRequestMatcher.matches(request); }
	 */
}
