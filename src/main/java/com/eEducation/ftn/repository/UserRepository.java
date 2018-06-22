package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	  public User findByUsername(String username);
}
