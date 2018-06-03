package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
