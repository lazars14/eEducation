package com.eEducation.ftn.web.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.CourseFile;
import com.eEducation.ftn.model.Notification;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.StudentAttendsCourse;
import com.eEducation.ftn.repository.NotificationRepository;
import com.eEducation.ftn.repository.StudentAttendsCourseRepository;
import com.eEducation.ftn.service.CourseFileService;
import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.FileService;
import com.eEducation.ftn.service.NotificationService;
import com.eEducation.ftn.service.StudentAttendsCourseService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.NotificationDTO;

@RestController
@RequestMapping(value="api/notifications")
public class NotificationController {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@Autowired
	CourseFileService courseFileService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentAttendsCourseService sacService;
	
	@Autowired
	StudentAttendsCourseRepository sacRepository;
	
	@Autowired
	FileService fileService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<NotificationDTO>> getAll(){
		List<Notification> notifications = notificationService.findAll();
		List<NotificationDTO> notificationDTOs = new ArrayList<>();
		
		for(Notification n : notifications) {
			notificationDTOs.add(new NotificationDTO(n));
		}
		
		return new ResponseEntity<>(notificationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<NotificationDTO> getById(@PathVariable Long id){
		Notification found = notificationService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new NotificationDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}/read")
	public ResponseEntity<NotificationDTO> readNotification(@PathVariable Long id){
		Notification found = notificationService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		// set notification to seen
		found.setSeen(true);
		notificationService.save(found);
		
		return new ResponseEntity<>(new NotificationDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value="/course/{courseId}/batchAdd")
	public ResponseEntity<Void> batchAdd(@PathVariable Long courseId, @RequestParam("message") String message, @RequestParam("file") MultipartFile file){
		if(message.equals("") || message == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		CourseFile courseFile = null;
		if(file.isEmpty() == false) {
			// upload file
			String[] uploadResult = fileService.upload(file);
			
			if(uploadResult[0].equals("invalid")) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			// create course file
			courseFile = new CourseFile();
			courseFile.setCourse(course);
			courseFile.setCourseLesson(null);
			courseFile.setDocumentName(uploadResult[1]);
			courseFile.setDocumentType("notification");
			courseFile.setMimeType(uploadResult[0]);
			courseFile.setDocumentURL(fileService.getFolderPath() + file.getOriginalFilename());
			
			courseFileService.save(courseFile);
			System.out.println("course file original name is " + courseFile.getDocumentName());
		}
		
		List<StudentAttendsCourse> sacS = sacRepository.findByCourse(course);
		
		for(StudentAttendsCourse sac : sacS) {
			Notification newNotification = new Notification();
			newNotification.setMessage(message);
			newNotification.setNDate(new Date());
			newNotification.setCourse(course);
			// seen is false on creation - student hasn't seen it yet
			newNotification.setSeen(false);
			newNotification.setDocument(courseFile);
			// set student from for loop
			newNotification.setStudent(sac.getStudent());
			
			notificationService.save(newNotification);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value="/course/{courseId}/batchUpdate")
	public ResponseEntity<Void> batchEdit(@PathVariable Long courseId, @RequestParam("notificationId") String notificationId, @RequestParam("message") String message, @RequestParam(value="file", required=false) MultipartFile file){		
		Long notificationIdLong = Long.parseLong(notificationId);
		
		if(message.equals("") || message == null || notificationIdLong == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Notification found = notificationService.findOne(notificationIdLong);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		CourseFile newCourseFile = null;
		
		if(file != null) {
			
			// delete course file - file
			boolean deleted = false;
			try {
				deleted = fileService.delete(found.getDocument().getDocumentName());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(deleted == false) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			// upload file
			String[] uploadResult = fileService.upload(file);
			
			if(uploadResult[0].equals("invalid")) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			// create course file
			newCourseFile = new CourseFile();
			newCourseFile.setCourse(course);
			newCourseFile.setCourseLesson(null);
			newCourseFile.setDocumentName(uploadResult[1]);
			newCourseFile.setDocumentType("notification");
			newCourseFile.setMimeType(uploadResult[0]);
			newCourseFile.setDocumentURL(fileService.getFolderPath() + file.getOriginalFilename());
			
			courseFileService.save(newCourseFile);
		}
		
		List<Notification> notifications = notificationRepository.findByCourseAndMessage(course, found.getMessage());
		
		for(Notification notification : notifications) {
			notification.setNDate(new Date());
			notification.setMessage(message);
			// seen is false on update - student hasn't seen it yet
			notification.setSeen(false);
			
			if(newCourseFile != null) {
				notification.setDocument(newCourseFile);
			}
			
			notificationService.save(notification);
		}
		
		if(newCourseFile != null) {
			// delete old course file
			courseFileService.remove(found.getDocument().getId());
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/batchDelete")
	public ResponseEntity<Void> batchDelete(@RequestBody NotificationDTO notification){
		if(notification == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Course course = courseService.findOne(notification.getCourse().getId());
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// delete course file
		boolean deleted = false;
		try {
			deleted = fileService.delete(notification.getDocument().getDocumentName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		if(deleted == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Notification> notifications = notificationRepository.findByCourseAndMessage(course, notification.getMessage());
		
		for(Notification n : notifications) {
			// remove notification
			notificationService.remove(n.getId());
		}
		
		courseFileService.remove(notification.getDocument().getId());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	public ResponseEntity<NotificationDTO> update(@RequestBody NotificationDTO notification){
		Notification found = notificationService.findOne(notification.getId());
		found.setMessage(notification.getMessage());
		found.setNDate(notification.getnDate());
		
		// not allowed to change course or student
		
		if(notification.getDocument() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		CourseFile courseFile = courseFileService.findOne(notification.getDocument().getId());
		
		if(courseFile == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setSeen(false);
		found.setDocument(courseFile);
		
		notificationService.save(found);
		return new ResponseEntity<>(new NotificationDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Notification found = notificationService.findOne(id);
		if(found != null) {
			notificationService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/student/{studentId}")
	public ResponseEntity<List<NotificationDTO>> getByStudent(@PathVariable Long studentId){
		Student student = studentService.findOne(studentId);
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Notification> notifications = notificationRepository.findByStudent(student);
		List<NotificationDTO> notificationDTOs = new ArrayList<>();
		
		for(Notification n : notifications) {
			notificationDTOs.add(new NotificationDTO(n));
		}
		
		return new ResponseEntity<>(notificationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/course/{courseId}/student/{studentId}")
	public ResponseEntity<List<NotificationDTO>> getByCourseAndStudent(@PathVariable Long studentId, @PathVariable Long courseId){
		Student student = studentService.findOne(studentId);
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Notification> notifications = notificationRepository.findByCourseAndStudent(course, student);
		List<NotificationDTO> notificationDTOs = new ArrayList<>();
		
		for(Notification n : notifications) {
			notificationDTOs.add(new NotificationDTO(n));
		}
		
		return new ResponseEntity<>(notificationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/course/{courseId}/distinctMessages")
	public ResponseEntity<List<NotificationDTO>> getByCourseDistinct(@PathVariable Long courseId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<StudentAttendsCourse> sac = sacRepository.findByCourse(course);
		
		List<NotificationDTO> notificationDTOs = new ArrayList<>();
				
		if(sac.size() == 0) return new ResponseEntity<>(notificationDTOs, HttpStatus.OK);
		
		Student student = sac.get(0).getStudent();
		
		List<Notification> notifications = notificationRepository.findByCourseAndStudent(course, student);
		
		for(Notification notification : notifications) {
			notificationDTOs.add(new NotificationDTO(notification));
		}
		
		return new ResponseEntity<>(notificationDTOs, HttpStatus.OK);
	}
}
