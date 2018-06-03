package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.CollegeDirection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	public Student findByEmail(String email);
	public Student findByIndexNumber(String indexNumber);
	public Student findByAccountNumber(String accountNumber);
	public Student findByReferenceNumber(String referenceNumber);
	public List<Student> findByDirection(CollegeDirection direction);
}
