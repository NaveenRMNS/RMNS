package com.punchershop.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punchershop.model.User;
import com.punchershop.service.UserService;

/**
 * @author Naveen
 *
 */
@RestController
public class UserController {

	@Autowired
	UserService userService;


	@GetMapping(value="/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> usersList = userService.findAllUsers();
		return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);

	}

	@GetMapping(value="/getuser")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<User> getUser(Principal principal ) {
		User user = userService.getUserByEmail(principal.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}



}
