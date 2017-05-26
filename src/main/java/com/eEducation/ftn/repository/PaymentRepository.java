package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
