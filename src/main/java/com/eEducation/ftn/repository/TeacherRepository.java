package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	public Teacher findByEmail(String email);
}
