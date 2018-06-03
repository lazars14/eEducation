package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.ExamTerm;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.StudentExamEntry;

@Repository
public interface StudentExamEntryRepository extends JpaRepository<StudentExamEntry, Long> {
	public List<StudentExamEntry> findByExamTermAndStudent(ExamTerm examTerm, Student student);
	public List<StudentExamEntry> findByExamTerm(ExamTerm examTerm);
}
