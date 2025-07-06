
package com.learning.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.security.entity.User;
import com.learning.security.model.UserPrinciple;
import com.learning.security.repository.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByusername(username);
		return new UserPrinciple(user);
	}

}
