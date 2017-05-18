package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.Exam;
import com.eEducation.ftn.repository.ExamRepository;

@Service
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	
	public Exam findOne(Long id) {
		return examRepository.findOne(id);
	}

	public List<Exam> findAll() {
		return examRepository.findAll();
	}
	
	public Page<Exam> findAll(Pageable page) {
		return examRepository.findAll(page);
	}

	public Exam save(Exam exam) {
		return examRepository.save(exam);
	}

	public void remove(Long id) {
		examRepository.delete(id);
	}
}
