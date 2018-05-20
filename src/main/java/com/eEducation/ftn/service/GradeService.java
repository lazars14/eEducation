package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.Grade;
import com.eEducation.ftn.repository.GradeRepository;

@Service
public class GradeService {
	@Autowired
	GradeRepository gradeRepository;
	
	public Grade findOne(Integer id) {
		return gradeRepository.findOne(id);
	}

	public List<Grade> findAll() {
		return gradeRepository.findAll();
	}
	
	public Page<Grade> findAll(Pageable page) {
		return gradeRepository.findAll(page);
	}

	public Grade save(Grade grade) {
		return gradeRepository.save(grade);
	}

	public void remove(Integer id) {
		gradeRepository.delete(id);
	}
}
