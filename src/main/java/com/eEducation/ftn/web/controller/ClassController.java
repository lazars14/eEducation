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
	public ResponseEntity<List<ClassDTO>> getAll(){
		List<Class> classes = classService.findAll();
		List<ClassDTO> classDTOs = new ArrayList<>();
		
		for(Class c : classes){
			classDTOs.add(new ClassDTO(c));
		}
		
		return new ResponseEntity(classDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<ClassDTO> getById(@PathVariable Integer id){
		Class found = classService.findOne(id);
		if(found == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new ClassDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ClassDTO> save(@RequestBody ClassDTO classs){
		Class newClass = new Class();
		newClass.setName(classs.getName());
		newClass.setNumOfYears(classs.getNumOfYears());
		
		classService.save(newClass);
		return new ResponseEntity<>(new ClassDTO(newClass), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<ClassDTO> update(@RequestBody ClassDTO classs){
		Class found = classService.findOne(classs.getId());
		if(found == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setName(classs.getName());
		found.setNumOfYears(classs.getNumOfYears());
		
		classService.save(found);
		return new ResponseEntity<>(new ClassDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		Class found = classService.findOne(id);
		if(found != null){
			classService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
