package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.AppUser;
import com.eEducation.ftn.repository.AppUserRepository;

@Service
public class AppUserService {
	@Autowired
	AppUserRepository appUserRepository;
	
	public AppUser findOne(Long id) {
		return appUserRepository.findOne(id);
	}

	public List<AppUser> findAll() {
		return appUserRepository.findAll();
	}
	
	public Page<AppUser> findAll(Pageable page) {
		return appUserRepository.findAll(page);
	}

	public AppUser save(AppUser appUser) {
		return appUserRepository.save(appUser);
	}

	public void remove(Long id) {
		appUserRepository.delete(id);
	}
}