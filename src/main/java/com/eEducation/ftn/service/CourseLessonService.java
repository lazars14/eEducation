package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.CourseLesson;
import com.eEducation.ftn.repository.CourseLessonRepository;

@Service
public class CourseLessonService {
	@Autowired
	CourseLessonRepository courseLessonRepository;
	
	public CourseLesson findOne(Long id) {
		return courseLessonRepository.getOne(id);
	}

	public List<CourseLesson> findAll() {
		return courseLessonRepository.findAll();
	}
	
	public Page<CourseLesson> findAll(Pageable page) {
		return courseLessonRepository.findAll(page);
	}

	public CourseLesson save(CourseLesson courseLesson) {
		return courseLessonRepository.save(courseLesson);
	}

	public void remove(Long id) {
		courseLessonRepository.delete(courseLessonRepository.getOne(id));
	}
}
