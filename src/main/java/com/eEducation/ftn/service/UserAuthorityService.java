package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.UserAuthority;
import com.eEducation.ftn.repository.UserAuthorityRepository;

@Service
public class UserAuthorityService {
	@Autowired
	UserAuthorityRepository userAuthorityRepository;
	
	public UserAuthority findOne(Long id) {
		return userAuthorityRepository.getOne(id);
	}

	public List<UserAuthority> findAll() {
		return userAuthorityRepository.findAll();
	}
	
	public Page<UserAuthority> findAll(Pageable page) {
		return userAuthorityRepository.findAll(page);
	}

	public UserAuthority save(UserAuthority userAuthority) {
		return userAuthorityRepository.save(userAuthority);
	}

	public void remove(Long id) {
		userAuthorityRepository.delete(userAuthorityRepository.getOne(id));
	}
}
