package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.Class;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	public Student findByEmail(String email);
	public Student findByIndexNumber(String indexNumber);
	public Student findByAccountNumber(String accountNumber);
	public Student findByClasss(Class classs);
}
