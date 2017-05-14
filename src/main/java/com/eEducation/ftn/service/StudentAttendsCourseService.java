package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.StudentAttendsCourse;
import com.eEducation.ftn.repository.StudentAttendsCourseRepository;

@Service
public class StudentAttendsCourseService {
	@Autowired
	StudentAttendsCourseRepository sacRepository;
	
	public StudentAttendsCourse findOne(Long id) {
		return sacRepository.findOne(id);
	}

	public List<StudentAttendsCourse> findAll() {
		return sacRepository.findAll();
	}
	
	public Page<StudentAttendsCourse> findAll(Pageable page) {
		return sacRepository.findAll(page);
	}

	public StudentAttendsCourse save(StudentAttendsCourse sac) {
		return sacRepository.save(sac);
	}

	public void remove(Long id) {
		sacRepository.delete(id);
	}
}
