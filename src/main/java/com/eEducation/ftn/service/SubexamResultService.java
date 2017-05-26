package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.SubexamResult;
import com.eEducation.ftn.repository.SubexamResultRepository;

@Service
public class SubexamResultService {
	@Autowired
	SubexamResultRepository srRepository;
	
	public SubexamResult findOne(Long id) {
		return srRepository.findOne(id);
	}

	public List<SubexamResult> findAll() {
		return srRepository.findAll();
	}
	
	public Page<SubexamResult> findAll(Pageable page) {
		return srRepository.findAll(page);
	}

	public SubexamResult save(SubexamResult sr) {
		return srRepository.save(sr);
	}

	public void remove(Long id) {
		srRepository.delete(id);
	}
}
