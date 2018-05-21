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

import com.eEducation.ftn.service.CourseFileService;
import com.eEducation.ftn.service.CourseLessonService;
import com.eEducation.ftn.web.dto.CourseFileDTO;

@RestController
@RequestMapping(value="api/courses")
public class CourseController {
	@Autowired
	CourseFileService courseFileService;
	
	@Autowired
	CourseLessonService courseLessonService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CourseFileDTO>> getAll(){
		List<CourseFile> courseFiles = courseFileService.findAll();
		List<CourseFileDTO> courseFileDTOs = new ArrayList<>();
		
		for(CourseFile cf : courseFiles) {
			courseFileDTOs.add(new CourseFileDTO(cf));
		}
		
		return new ResponseEntity<>(courseFileDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<CourseFileDTO> getById(@PathVariable Integer id){
		CourseFile found = courseFileService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<>(new CourseFileDTO(found), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CourseFileDTO> save(@RequestBody CourseFileDTO courseFile){
		CourseFile newCourseFile = new CourseFile();
		
		if(courseFile.getCourseLesson() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		CourseLesson courseLesson = courseLessonService.findOne(courseFile.getCourseLesson().getId());
		if(courseLesson == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newCourseFile.setCourseLesson(courseLesson);
		newCourseFile.setDocumentName(courseFile.getDocumentName());
		newCourseFile.setDocumentType(courseFile.getDocumentType());
		newCourseFile.setDocumentUrl(courseFile.getDocumentUrl());
		newCourseFile.setMimeType(course.getEspbPoints());
		
		courseFileService.save(newCourseFile);
		return new ResponseEntity<>(new CourseFileDTO(newCourseFile), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<CourseFileDTO> update(@RequestBody CourseFileDTO courseFile){
		CourseFile found = courseFileService.findOne(courseFile.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// not allowed to change courseLesson
		
		found.setDocumentName(courseFile.getDocumentName());
		found.setDocumentType(courseFile.getDocumentType());
		found.setDocumentUrl(courseFile.getDocumentUrl());
		found.setMimeType(course.getEspbPoints());
		
		courseFileService.save(found);
		return new ResponseEntity<>(new CourseFileDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
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
