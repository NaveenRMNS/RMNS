package com.punchershop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punchershop.model.User;
import com.punchershop.response.PResponse;
import com.punchershop.service.UserService;

/**
 * @author Naveen
 *
 */
@RestController
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/registration")
	public ResponseEntity<PResponse> register(@RequestBody User userRequestObj) {
		User userObj = userService.saveUser(userRequestObj);
		if (userObj != null) {
			return new ResponseEntity<PResponse>(new PResponse("User is saved successfully"), HttpStatus.OK);
		}
		return null;
		
	}
	
}
