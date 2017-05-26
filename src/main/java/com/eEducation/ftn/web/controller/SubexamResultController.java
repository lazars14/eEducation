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

import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.service.SubexamResultService;
import com.eEducation.ftn.service.SubexamService;
import com.eEducation.ftn.web.dto.SubexamResultDTO;

@RestController
@RequestMapping(value="api/subexamResults")
public class SubexamResultController {
	@Autowired
	SubexamResultService serService;
	
	@Autowired
	SubexamService subexamService;
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SubexamResultDTO>> getSubexamResults(){
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<SubexamResultDTO> getSubexamResult(@PathVariable Long id){
		return null;
	
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<SubexamResultDTO> saveSubexamResult(@RequestBody SubexamResultDTO ser){
		return null;

	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<SubexamResultDTO> updateSubexamResult(@RequestBody SubexamResultDTO ser){
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSubexamResult(@PathVariable Long id){
		return null;
		
	}
	
	// collection methods
}
