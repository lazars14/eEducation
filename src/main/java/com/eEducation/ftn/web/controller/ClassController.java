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

import com.eEducation.ftn.service.ClassService;
import com.eEducation.ftn.web.dto.ClassDTO;

@RestController
@RequestMapping(value="api/classes")
public class ClassController {
	@Autowired
	ClassService classService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClassDTO>> getClasses(){
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<ClassDTO> getClass(@PathVariable Long id){
		return null;
	
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ClassDTO> saveClass(@RequestBody ClassDTO classs){
		return null;

	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<ClassDTO> updateClass(@RequestBody ClassDTO classs){
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteClass(@PathVariable Long id){
		return null;
		
	}
	
	// collection methods
}
