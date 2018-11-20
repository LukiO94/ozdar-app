package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDAO {
	
	@JsonProperty("username")
	private String name;
	
	@JsonProperty("password")
	private String password;
	
	public UserDAO(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
