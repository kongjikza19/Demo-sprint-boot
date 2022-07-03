package com.google.demo.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.google.demo.controller.request.UserRequest;
import com.google.demo.exception.UserDuplicateException;
import com.google.demo.model.User;
import com.google.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public User resigter(UserRequest userRequest) {
		User user = userRepository.findByUsername(userRequest.getUsername());
		if (user == null) {
			user = new User().setUsername(userRequest.getUsername())
					.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()))
					.setRole(userRequest.getRole());
			return userRepository.save(user);
		}
		throw new UserDuplicateException(userRequest.getUsername());
	}

	@Override
	public User findUserByUsername(String username) {
		Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

}
