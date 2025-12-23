package com.Security.usermanagement.dto;

public class RoleDto {

	private Long roleId;

	private String name;

	public Long getId() {
		return roleId;
	}

	public void setId(Long id) {
		this.roleId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RoleDto [id=" + roleId + ", name=" + name + "]";
	}
	
}
