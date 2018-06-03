package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.Payment;
import com.eEducation.ftn.model.Student;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	public List<Payment> findByStudent(Student student);
}
