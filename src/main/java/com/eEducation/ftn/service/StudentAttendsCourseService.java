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
	StudentAttendsCourseRepository satRepository;
	
	public StudentAttendsCourse findOne(Integer id) {
		return satRepository.findOne(id);
	}

	public List<StudentAttendsCourse> findAll() {
		return satRepository.findAll();
	}
	
	public Page<StudentAttendsCourse> findAll(Pageable page) {
		return satRepository.findAll(page);
	}

	public StudentAttendsCourse save(StudentAttendsCourse sat) {
		return satRepository.save(sat);
	}

	public void remove(Integer id) {
		satRepository.delete(id);
	}
}
