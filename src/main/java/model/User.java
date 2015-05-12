package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {
	private static final Logger logger = LoggerFactory.getLogger(User.class);
	private String userId;
	private String password;
	private String name;
	private String email;

	public User(String userId, String password, String name, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}

	public boolean login(String userId, String password) {
		if (!this.userId.equals(userId)) {
			logger.debug("userID none");
		}
		if (!this.password.equals(password)) {
			logger.debug("password not match");
		}
		return true;

	}
}
