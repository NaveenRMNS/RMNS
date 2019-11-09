package com.punchershop.response;

import java.io.Serializable;

import com.punchershop.model.User;

/**
 * @author Naveen
 *
 */
public class UserModel implements Serializable {

	private static final long serialVersionUID = -6990968273016277115L;

	private User userModel;

	private String token;

	public UserModel(User userModel, String token) {
		super();
		this.userModel = userModel;
		this.token = token;
	}

	public User getUserModel() {
		return userModel;
	}

	public void setUserModel(User userModel) {
		this.userModel = userModel;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
