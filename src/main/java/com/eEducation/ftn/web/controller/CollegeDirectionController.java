package com.eEducation.ftn.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eEducation.ftn.model.CollegeDirection;
import com.eEducation.ftn.repository.StudentRepository;
import com.eEducation.ftn.service.CollegeDirectionService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.CollegeDirectionDTO;

@RestController
@RequestMapping(value="api/classes")
public class CollegeDirectionController {
	
	private static final Logger logger = LoggerFactory.getLogger(CollegeDirectionController.class);
	
	@Autowired
	CollegeDirectionService directionService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentRepository studentRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CollegeDirectionDTO>> getAll(){
		List<CollegeDirection> CollegeDirectiones = directionService.findAll();
		List<CollegeDirectionDTO> CollegeDirectionDTOs = new ArrayList<>();
		
		for(CollegeDirection c : CollegeDirectiones){
			CollegeDirectionDTOs.add(new CollegeDirectionDTO(c));
		}
		
		return new ResponseEntity<>(CollegeDirectionDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<CollegeDirectionDTO> getById(@PathVariable Long id){
		CollegeDirection found = directionService.findOne(id);
		if(found == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new CollegeDirectionDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CollegeDirectionDTO> add(@RequestBody CollegeDirectionDTO CollegeDirections){
		CollegeDirection newCollegeDirection = new CollegeDirection();
		newCollegeDirection.setName(CollegeDirections.getName());
		newCollegeDirection.setNumOfYears(CollegeDirections.getNumOfYears());
		
		directionService.save(newCollegeDirection);
		return new ResponseEntity<>(new CollegeDirectionDTO(newCollegeDirection), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	public ResponseEntity<CollegeDirectionDTO> update(@RequestBody CollegeDirectionDTO CollegeDirections){
		CollegeDirection found = directionService.findOne(CollegeDirections.getId());
		if(found == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setName(CollegeDirections.getName());
		found.setNumOfYears(CollegeDirections.getNumOfYears());
		
		directionService.save(found);
		return new ResponseEntity<>(new CollegeDirectionDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		CollegeDirection found = directionService.findOne(id);
		if(found != null){
			try {
				directionService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
