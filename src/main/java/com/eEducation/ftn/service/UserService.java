package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.User;
import com.eEducation.ftn.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User findOne(Long id) {
		return userRepository.getOne(id);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Page<User> findAll(Pageable page) {
		return userRepository.findAll(page);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void remove(Long id) {
		userRepository.delete(userRepository.getOne(id));
	}
}
