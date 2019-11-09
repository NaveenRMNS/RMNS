package com.punchershop.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.punchershop.model.User;
import com.punchershop.repository.UserRepository;
import com.punchershop.util.PasswordUtil;

/**
 * @author Naveen
 *
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User saveUser(User userObj) {
		String encodedPassword = PasswordUtil.getPasswordHash(userObj.getPassword());
		userObj.setPassword(encodedPassword);
		userObj.setCreationDate(new Date());
		return userRepository.save(userObj);
	}

	@Override
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUserByEmail(String name) {
		return userRepository.findByEmailIgnoreCase(name);
	}
	
}
