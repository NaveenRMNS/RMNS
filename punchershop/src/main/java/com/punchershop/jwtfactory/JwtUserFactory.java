package com.punchershop.jwtfactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.punchershop.jwtfactory.model.JwtUser;
import com.punchershop.model.User;

/**
 * @author Naveen
 *
 */
public class JwtUserFactory {

	public static JwtUser create(User user) {
		return new JwtUser(user.getId(), user.getEmail(), user.getPassword(), user, mapToGrantedAutorities(new ArrayList<String>(Arrays.asList("ROLE" + user.getRole()))), user.isEnabled());
	}

	private static List<GrantedAuthority> mapToGrantedAutorities(ArrayList<String> authorities) {
		return authorities.stream().map(Authority -> new SimpleGrantedAuthority(Authority)).collect(Collectors.toList());
	}

}
