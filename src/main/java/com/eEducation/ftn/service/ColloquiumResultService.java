package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.ColloquiumResult;
import com.eEducation.ftn.repository.ColloquiumResultRepository;

@Service
public class ColloquiumResultService {
	@Autowired
	ColloquiumResultRepository crRepository;
	
	public ColloquiumResult findOne(Long id) {
		return crRepository.getOne(id);
	}

	public List<ColloquiumResult> findAll() {
		return crRepository.findAll();
	}
	
	public Page<ColloquiumResult> findAll(Pageable page) {
		return crRepository.findAll(page);
	}

	public ColloquiumResult save(ColloquiumResult cResult) {
		return crRepository.save(cResult);
	}

	public void remove(Long id) {
		crRepository.delete(crRepository.getOne(id));
	}
}
