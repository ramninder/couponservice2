package com.ramninder.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramninder.springcloud.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	public User findByEmail(String email);

}
