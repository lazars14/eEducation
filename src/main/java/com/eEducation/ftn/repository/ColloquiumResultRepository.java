package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.Colloquium;
import com.eEducation.ftn.model.ColloquiumResult;
import com.eEducation.ftn.model.Student;

@Repository
public interface ColloquiumResultRepository extends JpaRepository<ColloquiumResult, Long> {
	public ColloquiumResult findByStudentAndColloquium(Student student, Colloquium colloquium);
	public List<ColloquiumResult> findByColloquium(Colloquium colloquium);
}
