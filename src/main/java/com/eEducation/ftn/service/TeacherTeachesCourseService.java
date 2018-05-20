package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.TeacherTeachesCourse;
import com.eEducation.ftn.repository.TeacherTeachesCourseRepository;

@Service
public class TeacherTeachesCourseService {
	@Autowired
	TeacherTeachesCourseRepository ttcRepository;
	
	public TeacherTeachesCourse findOne(Integer id) {
		return ttcRepository.findOne(id);
	}

	public List<TeacherTeachesCourse> findAll() {
		return ttcRepository.findAll();
	}
	
	public Page<TeacherTeachesCourse> findAll(Pageable page) {
		return ttcRepository.findAll(page);
	}

	public TeacherTeachesCourse save(TeacherTeachesCourse ttc) {
		return ttcRepository.save(ttc);
	}

	public void remove(Integer id) {
		ttcRepository.delete(id);
	}
}
