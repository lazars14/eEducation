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
import com.eEducation.ftn.service.GradeService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.GradeDTO;

@RestController
@RequestMapping(value="api/grades")
public class GradeController {
	@Autowired
	GradeService gradeService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<GradeDTO>> getGrades(){
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<GradeDTO> getGrade(@PathVariable Long id){
		return null;
	
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<GradeDTO> saveGrade(@RequestBody GradeDTO grade){
		return null;

	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<GradeDTO> updateGrade(@RequestBody GradeDTO grade){
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteGrade(@PathVariable Long id){
		return null;
		
	}
	
	// collection methods
}
