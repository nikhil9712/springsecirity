package com.learning.security.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.learning.security.entity.User;

public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = 1L;
	/*
	 * By Default Spring Security provides Build in class UserDetails similar to
	 * UserDetailsService
	 * 
	 */

	/**
	 * 
	 */
	private User user;

	public UserPrinciple(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("ADMIN"));
	}

	@Override
	public String getPassword() {
		//return "K@123";
		return user.getPasseword();
	}

	@Override
	public String getUsername() {
		//return "Krushna";
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
	 * authenticated.
	 * 
	 * @return <code>true</code> if the user is not locked, <code>false</code>
	 *         otherwise
	 */
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Indicates whether the user's credentials (password) has expired. Expired
	 * credentials prevent authentication.
	 * 
	 * @return <code>true</code> if the user's credentials are valid (ie
	 *         non-expired), <code>false</code> if no longer valid (ie expired)
	 */
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user is enabled or disabled. A disabled user cannot be
	 * authenticated.
	 * 
	 * @return <code>true</code> if the user is enabled, <code>false</code>
	 *         otherwise
	 */
	public boolean isEnabled() {
		return true;
	}

}
