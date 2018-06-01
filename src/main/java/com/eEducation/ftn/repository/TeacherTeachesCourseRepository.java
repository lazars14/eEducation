package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.Teacher;
import com.eEducation.ftn.model.TeacherTeachesCourse;

public interface TeacherTeachesCourseRepository extends JpaRepository<TeacherTeachesCourse, Integer> {
	public List<TeacherTeachesCourse> findByTeacher(Teacher teacher);
	public List<TeacherTeachesCourse> findByCourse(Course course);
	public TeacherTeachesCourse findByTeacherAndCourse(Teacher teacher, Course course);
}
