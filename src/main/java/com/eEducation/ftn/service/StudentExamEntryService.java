package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.StudentExamEntry;
import com.eEducation.ftn.repository.StudentExamEntryRepository;

@Service
public class StudentExamEntryService {
	@Autowired
	StudentExamEntryRepository saeRepository;
	
	public StudentExamEntry findOne(Integer id) {
		return saeRepository.findOne(id);
	}

	public List<StudentExamEntry> findAll() {
		return saeRepository.findAll();
	}
	
	public Page<StudentExamEntry> findAll(Pageable page) {
		return saeRepository.findAll(page);
	}

	public StudentExamEntry save(StudentExamEntry sae) {
		return saeRepository.save(sae);
	}

	public void remove(Integer id) {
		saeRepository.delete(id);
	}
}
