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
import com.eEducation.ftn.model.TeacherTeachesCourse;
import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.TeacherService;
import com.eEducation.ftn.service.TeacherTeachesCourseService;
import com.eEducation.ftn.web.dto.TeacherTeachesCourseDTO;

@RestController
@RequestMapping(value="api/teacherTeachesCourse")
public class TeacherTeachesCourseController {
	@Autowired
	TeacherTeachesCourseService ttcService;
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TeacherTeachesCourseDTO>> getAll(){
		List<TeacherTeachesCourse> ttcS = ttcService.findAll();
		List<TeacherTeachesCourseDTO> ttcDTOs = new ArrayList<>();
		
		for(TeacherTeachesCourse ttc : ttcS) {
			ttcDTOs.add(new TeacherTeachesCourseDTO(ttc));
		}
		
		return new ResponseEntity<>(ttcDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<TeacherTeachesCourseDTO> getById(@PathVariable Integer id){
		TeacherTeachesCourse found = ttcService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new TeacherTeachesCourseDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<TeacherTeachesCourseDTO> save(@RequestBody TeacherTeachesCourseDTO ttc){
		TeacherTeachesCourse newTtc = new TeacherTeachesCourse();
		
		if(ttc.getTeacher() == null || ttc.getCourse() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Teacher teacher = teacherService.findOne(ttc.getTeacher().getId());
		Course course = courseService.findOne(ttc.getCourse().getId());
		
		if(teacher == null || course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newTtc.setTeacher(teacher);
		newTtc.setCourse(course);
		
		ttcService.save(newTtc);
		return new ResponseEntity<>(new TeacherTeachesCourseDTO(newTtc), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<TeacherTeachesCourseDTO> update(@RequestBody TeacherTeachesCourseDTO ttc){
		TeacherTeachesCourse found = ttcService.findOne(ttc.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(ttc.getTeacher() == null || ttc.getCourse() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Teacher teacher = teacherService.findOne(ttc.getTeacher().getId());
		Course course = courseService.findOne(ttc.getCourse().getId());
		
		if(teacher == null || course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setTeacher(teacher);
		found.setCourse(course);
		
		ttcService.save(found);
		return new ResponseEntity<>(new TeacherTeachesCourseDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		TeacherTeachesCourse found = ttcService.findOne(id);
		if(found != null) {
			ttcService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
	
}
