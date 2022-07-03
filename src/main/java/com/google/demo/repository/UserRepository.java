package com.google.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.google.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// select * from user where username = 'fool'
	User findByUsername(String username);

}
