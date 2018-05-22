package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.StudentDocument;

public interface StudentDocumentRepository extends JpaRepository<StudentDocument, Integer> {

}
