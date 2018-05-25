package com.eEducation.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Notification;
import com.eEducation.ftn.model.Student;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
	public List<Notification> findByStudent(Student student);
}
