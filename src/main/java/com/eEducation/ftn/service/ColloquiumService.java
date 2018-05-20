package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.Colloquium;
import com.eEducation.ftn.repository.ColloquiumRepository;

@Service
public class ColloquiumService {
	@Autowired
	ColloquiumRepository cRepository;
	
	public Colloquium findOne(Integer id) {
		return cRepository.findOne(id);
	}

	public List<Colloquium> findAll() {
		return cRepository.findAll();
	}
	
	public Page<Colloquium> findAll(Pageable page) {
		return cRepository.findAll(page);
	}

	public Colloquium save(Colloquium c) {
		return cRepository.save(c);
	}

	public void remove(Integer id) {
		cRepository.delete(id);
	}
}
