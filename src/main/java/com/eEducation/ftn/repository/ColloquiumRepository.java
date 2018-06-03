package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.Colloquium;
import com.eEducation.ftn.model.Course;

@Repository
public interface ColloquiumRepository extends JpaRepository<Colloquium, Long> {
	public List<Colloquium> findByCourse(Course course);
}
