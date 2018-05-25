package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.CourseLesson;

public interface CourseLessonRepository extends JpaRepository<CourseLesson, Integer> {
	public List<CourseLesson> findByCourse(Course course);
}
