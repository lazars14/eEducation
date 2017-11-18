package com.eEducation.ftn.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eEducation.ftn.service.ClassService;
import com.eEducation.ftn.service.CourseFileService;
import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.NotificationService;
import com.eEducation.ftn.web.dto.NotificationDTO;

@RestController
@RequestMapping(value="api/notifications")
public class NotificationController {
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	CourseFileService courseFileService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	ClassService classService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<NotificationDTO>> getNotifications(){
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<NotificationDTO> getNotification(@PathVariable Long id){
		return null;
	
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<NotificationDTO> saveNotification(@RequestBody NotificationDTO notification){
		return null;

	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<NotificationDTO> updateNotification(@RequestBody NotificationDTO notification){
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteNotification(@PathVariable Long id){
		return null;
		
	}
	
	// collection methods
}
