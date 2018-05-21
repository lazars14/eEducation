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

import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.CourseLessonService;
import com.eEducation.ftn.web.dto.CourseLessonDTO;

@RestController
@RequestMapping(value="api/courseLessons")
public class CourseLessonController {
	@Autowired
	CourseLessonService courseLessonService;
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CourseDTO>> getCourseLessons(){
		List<CourseLesson> courseLessons = courseLessonService.findAll();
		List<CourseLessonDTO> courseLessonDTOs = new ArrayList<>();
		
		for(CourseLesson cl : courseLessons) {
			courseLessonDTOs.add(new CourseLessonDTO(cl));
		}
		
		return new ResponseEntity<>(courseLessonDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<CourseDTO> getCourseLesson(@PathVariable Integer id){
		CourseLesson found = courseLessonService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<>(new CourseLessonDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CourseDTO> saveCourseLesson(@RequestBody CourseLessonDTO courseLesson){
		CourseLesson newCourseLesson = new CourseLesson();
		
		if(courseLesson.getCourseId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Course course = courseService.findOne(courseLesson.getCourseId().getId());
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newCourseLesson.setCourseId(course);
		newCourseLesson.setName(courseLesson.getName());
		newCourseLesson.setDescription(course.getDescription());
		
		courseLessonService.save(newCourseLesson);
		return new ResponseEntity<>(new CourseLessonDTO(newCourseLesson), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<CourseDTO> updateCourseLesson(@RequestBody CourseLessonDTO courseLesson){
		CourseLesson found = courseLessonService.findOne(courseLesson.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// not allowed to change course
		
		found.setName(courseLesson.getName());
		found.setDescription(course.getDescription());
		
		courseLessonService.save(found);
		return new ResponseEntity<>(new CourseLessonDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCourseLesson(@PathVariable Integer id){
		CourseLesson found = courseLessonService.findOne(id);
		if(found != null) {
			courseLessonService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
