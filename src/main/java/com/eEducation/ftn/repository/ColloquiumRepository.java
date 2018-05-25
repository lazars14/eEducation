package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Colloquium;
import com.eEducation.ftn.model.Course;

public interface ColloquiumRepository extends JpaRepository<Colloquium, Integer> {
	public List<Colloquium> findByCourse(Course course);
}
