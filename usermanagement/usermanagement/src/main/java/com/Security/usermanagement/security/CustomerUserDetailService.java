package com.Security.usermanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Security.usermanagement.repository.UserEntityRepository;
@Service
public class CustomerUserDetailService implements UserDetailsService {

	@Autowired
	UserEntityRepository entityRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails details = entityRepository.findByUserName(username);
		return details;
	}

}
