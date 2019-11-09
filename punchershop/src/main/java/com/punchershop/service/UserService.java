package com.punchershop.service;

import java.util.List;

import com.punchershop.model.User;

/**
 * @author Naveen
 *
 */
public interface UserService {

	User saveUser(User user);

	List<User> findAllUsers();

	User getUserByEmail(String name);

}
