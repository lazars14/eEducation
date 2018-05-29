package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.ExamPeriod;
import com.eEducation.ftn.model.ExamTerm;

public interface ExamTermRepository extends JpaRepository<ExamTerm, Integer> {
	public List<ExamTerm> findByExamPeriod(ExamPeriod examPeriod);
}
