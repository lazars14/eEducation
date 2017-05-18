package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.Class;
import com.eEducation.ftn.repository.ClassRepository;

@Service
public class ClassService {
	@Autowired
	ClassRepository classRepository;
	
	public Class findOne(Long id) {
		return classRepository.findOne(id);
	}

	public List<Class> findAll() {
		return classRepository.findAll();
	}
	
	public Page<Class> findAll(Pageable page) {
		return classRepository.findAll(page);
	}

	public Class save(Class Class) {
		return classRepository.save(Class);
	}

	public void remove(Long id) {
		classRepository.delete(id);
	}
}
