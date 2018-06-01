package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.StudentAttendsCourse;

public interface StudentAttendsCourseRepository extends JpaRepository<StudentAttendsCourse, Integer>{
	public List<StudentAttendsCourse> findByStudent(Student student);
	public List<StudentAttendsCourse> findByCourse(Course course);
}
