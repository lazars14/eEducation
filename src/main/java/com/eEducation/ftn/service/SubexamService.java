package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.Subexam;
import com.eEducation.ftn.repository.SubexamRepository;

@Service
public class SubexamService {
	@Autowired
	SubexamRepository subexamRepository;
	
	public Subexam findOne(Long id) {
		return subexamRepository.findOne(id);
	}

	public List<Subexam> findAll() {
		return subexamRepository.findAll();
	}
	
	public Page<Subexam> findAll(Pageable page) {
		return subexamRepository.findAll(page);
	}

	public Subexam save(Subexam subexam) {
		return subexamRepository.save(subexam);
	}

	public void remove(Long id) {
		subexamRepository.delete(id);
	}
}