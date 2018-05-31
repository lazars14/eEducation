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

import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.CourseLesson;
import com.eEducation.ftn.repository.CourseLessonRepository;
import com.eEducation.ftn.service.CourseLessonService;
import com.eEducation.ftn.web.dto.CourseLessonDTO;

@RestController
@RequestMapping(value="api/course/{courseId}/courseLessons")
public class CourseLessonController {
	@Autowired
	CourseLessonService courseLessonService;
	
	@Autowired
	CourseLessonRepository courseLessonRepository;
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CourseLessonDTO>> getAll(@PathVariable Integer courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<CourseLesson> courseLessons = courseLessonService.findAll();
		List<CourseLessonDTO> courseLessonDTOs = new ArrayList<>();
		
		for(CourseLesson cl : courseLessons) {
			courseLessonDTOs.add(new CourseLessonDTO(cl));
		}
		
		return new ResponseEntity<>(courseLessonDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<CourseLessonDTO> getById(@PathVariable Integer id, @PathVariable Integer courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseLesson found = courseLessonService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<>(new CourseLessonDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CourseLessonDTO> add(@RequestBody CourseLessonDTO courseLesson, @PathVariable Integer courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseLesson newCourseLesson = new CourseLesson();
		
		if(courseLesson.getCourse() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Course course = courseService.findOne(courseLesson.getCourse().getId());
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newCourseLesson.setCourse(course);
		newCourseLesson.setName(courseLesson.getName());
		newCourseLesson.setDescription(courseLesson.getDescription());
		
		courseLessonService.save(newCourseLesson);
		return new ResponseEntity<>(new CourseLessonDTO(newCourseLesson), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<CourseLessonDTO> update(@RequestBody CourseLessonDTO courseLesson, @PathVariable Integer courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseLesson found = courseLessonService.findOne(courseLesson.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// not allowed to change course
		
		found.setName(courseLesson.getName());
		found.setDescription(courseLesson.getDescription());
		
		courseLessonService.save(found);
		return new ResponseEntity<>(new CourseLessonDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id, @PathVariable Integer courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseLesson found = courseLessonService.findOne(id);
		if(found != null) {
			courseLessonService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/byCourse")
	public ResponseEntity<List<CourseLessonDTO>> getByCourse(@PathVariable Integer courseId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<CourseLesson> courseLessons = courseLessonRepository.findByCourse(course);
		List<CourseLessonDTO> courseLessonDTOs = new ArrayList<>();
		
		for(CourseLesson cl : courseLessons) {
			courseLessonDTOs.add(new CourseLessonDTO(cl));
		}
		
		return new ResponseEntity<>(courseLessonDTOs, HttpStatus.OK);
	}
}
