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

import com.eEducation.ftn.model.CollegeDirection;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.repository.StudentRepository;
import com.eEducation.ftn.service.CollegeDirectionService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.CollegeDirectionDTO;
import com.eEducation.ftn.web.dto.StudentDTO;

@RestController
@RequestMapping(value="api/CollegeDirectiones")
public class CollegeDirectionController {
	@Autowired
	CollegeDirectionService CollegeDirectionService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentRepository studentRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CollegeDirectionDTO>> getAll(){
		List<CollegeDirection> CollegeDirectiones = CollegeDirectionService.findAll();
		List<CollegeDirectionDTO> CollegeDirectionDTOs = new ArrayList<>();
		
		for(CollegeDirection c : CollegeDirectiones){
			CollegeDirectionDTOs.add(new CollegeDirectionDTO(c));
		}
		
		return new ResponseEntity<>(CollegeDirectionDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<CollegeDirectionDTO> getById(@PathVariable Integer id){
		CollegeDirection found = CollegeDirectionService.findOne(id);
		if(found == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new CollegeDirectionDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CollegeDirectionDTO> save(@RequestBody CollegeDirectionDTO CollegeDirections){
		CollegeDirection newCollegeDirection = new CollegeDirection();
		newCollegeDirection.setName(CollegeDirections.getName());
		newCollegeDirection.setNumOfYears(CollegeDirections.getNumOfYears());
		
		CollegeDirectionService.save(newCollegeDirection);
		return new ResponseEntity<>(new CollegeDirectionDTO(newCollegeDirection), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<CollegeDirectionDTO> update(@RequestBody CollegeDirectionDTO CollegeDirections){
		CollegeDirection found = CollegeDirectionService.findOne(CollegeDirections.getId());
		if(found == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setName(CollegeDirections.getName());
		found.setNumOfYears(CollegeDirections.getNumOfYears());
		
		CollegeDirectionService.save(found);
		return new ResponseEntity<>(new CollegeDirectionDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		CollegeDirection found = CollegeDirectionService.findOne(id);
		if(found != null){
			CollegeDirectionService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}/students")
	public ResponseEntity<List<StudentDTO>> getStudentsForCollegeDirection(@PathVariable Integer id){
		CollegeDirection found = CollegeDirectionService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<Student> students = studentRepository.findByDirection(found);
		List<StudentDTO> studentDTOs = new ArrayList<>();
		
		for(Student s : students){
			studentDTOs.add(new StudentDTO(s));
		}
		
		return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
	}
}
