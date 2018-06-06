package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.CourseFile;
import com.eEducation.ftn.repository.CourseFileRepository;

@Service
public class CourseFileService {
	@Autowired
	CourseFileRepository courseFileRepository;
	
	public CourseFile findOne(Long id) {
		return courseFileRepository.getOne(id);
	}

	public List<CourseFile> findAll() {
		return courseFileRepository.findAll();
	}
	
	public Page<CourseFile> findAll(Pageable page) {
		return courseFileRepository.findAll(page);
	}

	public CourseFile save(CourseFile courseFile) {
		return courseFileRepository.save(courseFile);
	}

	public void remove(Long id) {
		courseFileRepository.delete(courseFileRepository.getOne(id));
	}
}
