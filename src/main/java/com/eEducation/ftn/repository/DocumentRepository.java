package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
