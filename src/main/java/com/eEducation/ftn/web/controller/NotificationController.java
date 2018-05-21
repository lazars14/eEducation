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
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<NotificationDTO>> getNotifications(){
		List<Notification> notifications = notificationService.findAll();
		List<NotificationDTO> notificationDTOs = new ArrayList<>();
		
		for(Notification n : notifications) {
			notificationDTOs.add(n);
		}
		
		return new ResponseEntity<>(notificationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<NotificationDTO> getNotification(@PathVariable Integer id){
		Notification found = notificationService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new NotificationDTO(found), HttpStatus.OK);
	}
	
//	private String message;
//  private Date nDate;
//  private CourseDTO courseId;
//  private CourseFileDTO documentId;
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<NotificationDTO> saveNotification(@RequestBody NotificationDTO notification){
		Notification newNotification = new Notification();
		newNotification.setMessage(notification.getMessage());
		newNotification.setNDate(notification.getNDate());
		
		if(notification.getCourseId() == null || notification.getDocumentId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Course course = courseService.findOne(notification.getCourseId().getId());
		CourseFile courseFile = courseFileService.findOne(notification.getDocumentId().getId());
		
		if(course == null || courseFile == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newNotification.setCourseId(course);
		newNotification.setDocumentId(courseFile);
		
		notificationService.save(newNotification);
		return new ResponseEntity<>(new NotificationDTO(newNotification), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<NotificationDTO> updateNotification(@RequestBody NotificationDTO notification){
		Notification found = notificationService.findOne(notification.getId());
		found.setMessage(notification.getMessage());
		found.setNDate(notification.getNDate());
		
		// not allowed to change course
		
		if(notification.getDocumentId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		CourseFile courseFile = courseFileService.findOne(notification.getDocumentId().getId());
		
		if(courseFile == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setDocumentId(courseFile);
		
		notificationService.save(found);
		return new ResponseEntity<>(new NotificationDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteNotification(@PathVariable Integer id){
		Notification found = notificationService.findOne(id);
		if(found != null) {
			notificationService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
