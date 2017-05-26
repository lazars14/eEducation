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

import com.eEducation.ftn.service.DocumentService;
import com.eEducation.ftn.service.ExamService;
import com.eEducation.ftn.service.SubexamService;
import com.eEducation.ftn.web.dto.SubexamDTO;

@RestController
@RequestMapping(value="api/subexams")
public class SubexamController {
	@Autowired
	SubexamService subexamService;
	
	@Autowired
	ExamService examService;
	
	@Autowired
	DocumentService documentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SubexamDTO>> getSubexams(){
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<SubexamDTO> getSubexam(@PathVariable Long id){
		return null;
	
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<SubexamDTO> saveSubexam(@RequestBody SubexamDTO subexam){
		return null;

	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<SubexamDTO> updateSubexam(@RequestBody SubexamDTO subexam){
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSubexam(@PathVariable Long id){
		return null;
		
	}
	
	// collection methods
}
