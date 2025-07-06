package com.learning.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "USERS")
@Entity
public class User {

	  
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY) // Allows auto-increment behavior
    private Integer id;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String passeword;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasseword() {
		return passeword;
	}

	public void setPasseword(String passeword) {
		this.passeword = passeword;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", passeword=" + passeword + "]";
	}

}
