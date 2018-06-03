package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.StudentDocument;
import com.eEducation.ftn.repository.StudentDocumentRepository;

@Service
public class StudentDocumentService {
	@Autowired
	StudentDocumentRepository sdRepository;
	
	public StudentDocument findOne(Long id) {
		return sdRepository.findOne(id);
	}

	public List<StudentDocument> findAll() {
		return sdRepository.findAll();
	}
	
	public Page<StudentDocument> findAll(Pageable page) {
		return sdRepository.findAll(page);
	}

	public StudentDocument save(StudentDocument doc) {
		return sdRepository.save(doc);
	}

	public void remove(Long id) {
		sdRepository.delete(id);
	}
}
