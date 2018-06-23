package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.Authority;
import com.eEducation.ftn.repository.AuthorityRepository;

@Service
public class AuthorityService {
	@Autowired
	AuthorityRepository authorityRepository;
	
	public Authority findOne(Long id) {
		return authorityRepository.getOne(id);
	}

	public List<Authority> findAll() {
		return authorityRepository.findAll();
	}
	
	public Page<Authority> findAll(Pageable page) {
		return authorityRepository.findAll(page);
	}

	public Authority save(Authority authority) {
		return authorityRepository.save(authority);
	}

	public void remove(Long id) {
		authorityRepository.delete(authorityRepository.getOne(id));
	}
}
