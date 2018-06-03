package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.CourseFile;
import com.eEducation.ftn.model.CourseLesson;

@Repository
public interface CourseFileRepository extends JpaRepository<CourseFile, Long> {
	public List<CourseFile> findByCourseLesson(CourseLesson courseLesson);
	public List<CourseFile> findByCourse(Course course);
}
