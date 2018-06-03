package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.CollegeDirection;
import com.eEducation.ftn.repository.CollegeDirectionRepository;

@Service
public class CollegeDirectionService {
	@Autowired
	CollegeDirectionRepository directionRepository;
	
	public CollegeDirection findOne(Long id) {
		return directionRepository.findOne(id);
	}

	public List<CollegeDirection> findAll() {
		return directionRepository.findAll();
	}
	
	public Page<CollegeDirection> findAll(Pageable page) {
		return directionRepository.findAll(page);
	}

	public CollegeDirection save(CollegeDirection direction) {
		return directionRepository.save(direction);
	}

	public void remove(Long id) {
		directionRepository.delete(id);
	}
}
