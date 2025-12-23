package com.Security.usermanagement.dto;

import java.util.Set;

public class SignUpDto {

	private String userName;

	private String password;
	
	private Set<RoleDto> roles;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "SignUpDto [userName=" + userName + ", password=" + password + ", roles=" + roles + "]";
	}
	
}
