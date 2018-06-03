package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.StudentDocument;

@Repository
public interface StudentDocumentRepository extends JpaRepository<StudentDocument, Long> {

}
