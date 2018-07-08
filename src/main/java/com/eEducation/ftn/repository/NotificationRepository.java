package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.Notification;
import com.eEducation.ftn.model.Student;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	public List<Notification> findByStudent(Student student);
	public List<Notification> findByCourse(Course course);
	public List<Notification> findByCourseAndStudent(Course course, Student student);
	public List<Notification> findByCourseAndMessage(Course course, String message);
}
