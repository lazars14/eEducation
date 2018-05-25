package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Payment;
import com.eEducation.ftn.model.Student;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	public List<Payment> findByStudent(Student student);
}
