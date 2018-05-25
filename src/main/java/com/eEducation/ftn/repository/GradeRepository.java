package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Grade;
import com.eEducation.ftn.model.Student;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
	public List<Grade> findByStudent(Student student);
}
