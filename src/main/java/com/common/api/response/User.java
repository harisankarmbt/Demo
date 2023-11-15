package com.common.api.response;

import javax.validation.constraints.NotBlank;		
import javax.validation.constraints.Size;

public class User {
	
	private Long id = 0L;
	
	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 50, message = "User name must be between 3 to 50 chareacters")
	private String username = "";
	
	@NotBlank(message = "Email is required")
	private String email = "";
	 
	public User() {
		
	}
	public User(Long id, String username, String email) {
		this.id = id;
		this.username=username;
		this.email=email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username=username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + "]";
	}
  }



