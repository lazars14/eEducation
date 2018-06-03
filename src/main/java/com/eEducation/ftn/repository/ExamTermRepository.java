package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.ExamPeriod;
import com.eEducation.ftn.model.ExamTerm;

@Repository
public interface ExamTermRepository extends JpaRepository<ExamTerm, Long> {
	public List<ExamTerm> findByExamPeriod(ExamPeriod examPeriod);
}
