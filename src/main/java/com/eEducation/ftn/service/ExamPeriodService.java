package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.ExamPeriod;
import com.eEducation.ftn.repository.ExamPeriodRepository;

@Service
public class ExamPeriodService {
	@Autowired
	ExamPeriodRepository examPeriodRepository;
	
	public ExamPeriod findOne(Long id) {
		return examPeriodRepository.findOne(id);
	}

	public List<ExamPeriod> findAll() {
		return examPeriodRepository.findAll();
	}
	
	public Page<ExamPeriod> findAll(Pageable page) {
		return examPeriodRepository.findAll(page);
	}

	public ExamPeriod save(ExamPeriod examPeriod) {
		return examPeriodRepository.save(examPeriod);
	}

	public void remove(Long id) {
		examPeriodRepository.delete(id);
	}
}
