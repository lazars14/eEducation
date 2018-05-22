package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
