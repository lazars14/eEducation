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
	public ResponseEntity<List<TeacherTeachesCourseDTO>> getTtcs(){
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<TeacherTeachesCourseDTO> getTtc(@PathVariable Long id){
		return null;
	
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<TeacherTeachesCourseDTO> saveTtc(@RequestBody TeacherTeachesCourseDTO ttc){
		return null;

	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<TeacherTeachesCourseDTO> updateTtc(@RequestBody TeacherTeachesCourseDTO ttc){
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTtc(@PathVariable Long id){
		return null;
		
	}
	
	// collection methods
	
}
