package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.Notification;
import com.eEducation.ftn.repository.NotificationRepository;

@Service
public class NotificationService {
	@Autowired
	NotificationRepository notificationRepository;
	
	public Notification findOne(Long id) {
		return notificationRepository.getOne(id);
	}

	public List<Notification> findAll() {
		return notificationRepository.findAll();
	}
	
	public Page<Notification> findAll(Pageable page) {
		return notificationRepository.findAll(page);
	}

	public Notification save(Notification notification) {
		return notificationRepository.save(notification);
	}

	public void remove(Long id) {
		notificationRepository.delete(notificationRepository.getOne(id));
	}
}
