package com.babel.bootcamp.travels.controllers.free.admin.impl;

import com.babel.bootcamp.travels.controllers.free.admin.UserController;
import com.babel.bootcamp.travels.model.Credentials;
import com.babel.bootcamp.travels.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travel-agency/admin/users")
public class UserControllerImpl implements UserController {

	private UserService userService;

	public UserControllerImpl(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("")
	@Override
	public void addUser(@RequestBody Credentials credentials) {
		userService.addUser(credentials);
	}
}
