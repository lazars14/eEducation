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
import com.eEducation.ftn.model.Teacher;
import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.TeacherService;
import com.eEducation.ftn.web.dto.CourseDTO;

@RestController
@RequestMapping(value="api/courses")
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@Autowired
	TeacherService teacherService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CourseDTO>> getAll(){
		List<Course> courses = courseService.findAll();
		List<CourseDTO> courseDTOs = new ArrayList<>();
		
		for(Course c : courses) {
			courseDTOs.add(new CourseDTO(c));
		}
		
		return new ResponseEntity<>(courseDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<CourseDTO> getById(@PathVariable Integer id){
		Course found = courseService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<>(new CourseDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CourseDTO> save(@RequestBody CourseDTO course){
		Course newCourse = new Course();
		
		if(course.getTeacher() == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Teacher teacher = teacherService.findOne(course.getTeacher().getId());
		
		if(teacher == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
				
		newCourse.setTeacher(teacher);
		newCourse.setCourseName(course.getCourseName());
		newCourse.setEspbPoints(course.getEspbPoints());
		
		courseService.save(newCourse);
		return new ResponseEntity<>(new CourseDTO(newCourse), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<CourseDTO> update(@RequestBody CourseDTO course){
		Course found = courseService.findOne(course.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(course.getTeacher() == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Teacher teacher = teacherService.findOne(course.getTeacher().getId());
		
		if(teacher == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
				
		found.setTeacher(teacher);
		found.setCourseName(course.getCourseName());
		found.setEspbPoints(course.getEspbPoints());
		
		courseService.save(found);
		return new ResponseEntity<>(new CourseDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		Course found = courseService.findOne(id);
		if(found != null) {
			courseService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
