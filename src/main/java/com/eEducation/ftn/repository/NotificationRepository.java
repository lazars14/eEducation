package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.Notification;
import com.eEducation.ftn.model.Student;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
	public List<Notification> findByStudent(Student student);
	public List<Notification> findByCourse(Course course);
	public List<Notification> findByCourseAndStudent(Course course, Student student);
	public List<Notification> findDistinctMessageByCourse(Course course);
	public List<Notification> findByCourseAndMessage(Course course, String message);
}
