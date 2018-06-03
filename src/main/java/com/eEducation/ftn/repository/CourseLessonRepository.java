package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.CourseLesson;

@Repository
public interface CourseLessonRepository extends JpaRepository<CourseLesson, Long> {
	public List<CourseLesson> findByCourse(Course course);
}
