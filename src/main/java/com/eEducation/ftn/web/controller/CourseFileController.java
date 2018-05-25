package com.eEducation.ftn.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.CourseFile;
import com.eEducation.ftn.model.CourseLesson;
import com.eEducation.ftn.service.CourseFileService;
import com.eEducation.ftn.service.CourseLessonService;
import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.web.dto.CourseFileDTO;

@RestController
@RequestMapping(value="api/courseFiles")
public class CourseFileController {
	@Autowired
	CourseFileService courseFileService;
	
	@Autowired
	CourseLessonService courseLessonService;
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CourseFileDTO>> getAll(@PathVariable Integer courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<CourseFile> courseFiles = courseFileService.findAll();
		List<CourseFileDTO> courseFileDTOs = new ArrayList<>();
		
		for(CourseFile cf : courseFiles) {
			courseFileDTOs.add(new CourseFileDTO(cf));
		}
		
		return new ResponseEntity<>(courseFileDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<CourseFileDTO> getById(@PathVariable Integer id, @PathVariable Integer courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseFile found = courseFileService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<>(new CourseFileDTO(found), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CourseFileDTO> add(@RequestBody CourseFileDTO courseFile, @PathVariable Integer courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseFile newCourseFile = new CourseFile();
		
		if(courseFile.getCourseLesson() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		CourseLesson courseLesson = courseLessonService.findOne(courseFile.getCourseLesson().getId());
		if(courseLesson == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(courseFile.getCourse() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Course course = courseService.findOne(courseFile.getCourse().getId());
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newCourseFile.setCourse(course);
		newCourseFile.setCourseLesson(courseLesson);
		newCourseFile.setDocumentName(courseFile.getDocumentName());
		newCourseFile.setDocumentType(courseFile.getDocumentType());
		newCourseFile.setDocumentURL(courseFile.getDocumentURL());
		newCourseFile.setMimeType(courseFile.getMimeType());
		
		courseFileService.save(newCourseFile);
		return new ResponseEntity<>(new CourseFileDTO(newCourseFile), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<CourseFileDTO> update(@RequestBody CourseFileDTO courseFile, @PathVariable Integer courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseFile found = courseFileService.findOne(courseFile.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// not allowed to change courseLesson and course
		
		found.setDocumentName(courseFile.getDocumentName());
		found.setDocumentType(courseFile.getDocumentType());
		found.setDocumentURL(courseFile.getDocumentURL());
		found.setMimeType(courseFile.getMimeType());
		
		courseFileService.save(found);
		return new ResponseEntity<>(new CourseFileDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id, @PathVariable Integer courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseFile found = courseFileService.findOne(id);
		if(found != null) {
			courseFileService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
