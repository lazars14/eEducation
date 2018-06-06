package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.Teacher;
import com.eEducation.ftn.repository.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	TeacherRepository teacherRepository;
	
	public Teacher findOne(Long id) {
		return teacherRepository.getOne(id);
	}

	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}
	
	public Page<Teacher> findAll(Pageable page) {
		return teacherRepository.findAll(page);
	}

	public Teacher save(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public void remove(Long id) {
		teacherRepository.delete(teacherRepository.getOne(id));
	}
}
