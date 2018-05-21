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

import com.eEducation.ftn.service.ColloquiumService;
import com.eEducation.ftn.web.dto.ColloquiumDTO;

@RestController
@RequestMapping(value="api/classes")
public class ColloquiumController {
	@Autowired
	ColloquiumService colloquiumService;
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClassDTO>> getColloquiums(){
		List<Colloquium> colloquiums = colloquiumService.findAll();
		List<ColloquiumDTO> colloquiumDTOs = new ArrayList<>();
		
		for(Colloquium c : colloquiums){
			colloquiumDTOs.add(new ColloquiumDTO(c));
		}
		
		return new ResponseEntity(colloquiumDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<ClassDTO> getColloquium(@PathVariable Integer id){
		Colloquium found = colloquiumService.findOne(id);
		if(found == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new ColloquiumDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ClassDTO> saveColloquium(@RequestBody ColloquiumDTO colloquium){
		Colloquium newColloquium = new Colloquium();
		
		if(colloquium.getCourseId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Course course = courseService.findOne(colloquium.getCourseId().getId());
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newColloquium.setCourseId(course);
		newColloquium.setMaxPoints(colloquium.getMaxPoints());
		newColloquium.setExamType(colloquium.getExamType());
		newColloquium.setExamDateTime(colloquium.getExamDateTime());
		
		colloquiumService.save(newColloquium);
		return new ResponseEntity<>(new ColloquiumDTO(newColloquium), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<ClassDTO> updateColloquium(@RequestBody ColloquiumDTO colloquium){
		Colloquium found = colloquiumService.findOne(colloquium.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// not allowed to change course
		
		found.setMaxPoints(colloquium.getMaxPoints());
		found.setExamType(colloquium.getExamType());
		found.setExamDateTime(colloquium.getExamDateTime());
		
		colloquiumService.save(found);
		return new ResponseEntity<>(new ColloquiumDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteColloquium(@PathVariable Integer id){
		Colloquium found = colloquiumService.findOne(id);
		if(found != null){
			colloquiumService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
