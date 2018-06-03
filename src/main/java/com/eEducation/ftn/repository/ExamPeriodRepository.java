package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.ExamPeriod;

@Repository
public interface ExamPeriodRepository extends JpaRepository<ExamPeriod, Long> {

}
