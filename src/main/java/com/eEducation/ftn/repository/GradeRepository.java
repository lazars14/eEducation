package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.Grade;
import com.eEducation.ftn.model.Student;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
	public List<Grade> findByStudent(Student student);
}
