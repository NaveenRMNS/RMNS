package com.punchershop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punchershop.exception.UnauthorizeException;
import com.punchershop.jwtfactory.JwtTokenUtil;
import com.punchershop.jwtfactory.model.JwtUser;
import com.punchershop.model.User;
import com.punchershop.response.UserModel;

/**
 * @author Naveen
 *
 */

@RestController
public class AuthenticationController {
	
	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	
	@RequestMapping(value="/login")
	public ResponseEntity<UserModel> login(@RequestBody User userRequestObj, HttpServletRequest request, HttpServletResponse response) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequestObj.getEmail(), userRequestObj.getPassword()));
			final JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token = jwtTokenUtil.generateToken(jwtUser);
			response.setHeader("Token", token);
			return new ResponseEntity<UserModel>(new UserModel(jwtUser.getUser(), token),HttpStatus.OK);
		} catch (Exception e) {
			throw new UnauthorizeException(e.getMessage());
		}
	}
	
}
