package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
