package com.babel.bootcamp.travels.model;

public class Credentials {
	private String userName;
	private String password;

	public Credentials(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public Credentials() {
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
