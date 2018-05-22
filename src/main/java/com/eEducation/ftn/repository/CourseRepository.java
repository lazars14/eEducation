package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
