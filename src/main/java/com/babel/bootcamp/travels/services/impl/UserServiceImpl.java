package com.babel.bootcamp.travels.services.impl;

import com.babel.bootcamp.travels.model.Credentials;
import com.babel.bootcamp.travels.services.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private UserDetailsManager userDetailsManager;

	public UserServiceImpl(UserDetailsManager userDetailsManager) {
		this.userDetailsManager = userDetailsManager;
	}

	@Override
	public void addUser(Credentials credentials) {

		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		String password = passwordEncoder.encode(credentials.getPassword());

		UserDetails user = User
				.withUsername(credentials.getUserName())
				.password(password)
				.roles("USER")
				.build();

		userDetailsManager.createUser(user);
	}
}
