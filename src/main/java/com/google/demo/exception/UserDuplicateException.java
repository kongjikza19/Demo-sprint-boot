package com.google.demo.exception;

public class UserDuplicateException extends RuntimeException {

	public UserDuplicateException(String username) {
		super("Username : " + username + " already exists.");
	}

}
