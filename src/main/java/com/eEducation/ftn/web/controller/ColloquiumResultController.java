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

import com.eEducation.ftn.service.ColloquiumResultService;
import com.eEducation.ftn.web.dto.ColloquiumResultDTO;

@RestController
@RequestMapping(value="api/colloquiumResults")
public class ColloquiumResult.Controller {
	@Autowired
	ColloquiumResultService colloquiumResultService;
	
	@Autowired
	ColloquiumService colloquiumService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentDocumentService studentDocumentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClassDTO>> getColloquiumResulta(){
		List<ColloquiumResult> results = ColloquiumResultService.findAll();
		List<ClassDTO> resultDTOs = new ArrayList<>();
		
		for(ColloquiumResult c : collo){
			resultDTOs.add(new ClassDTO(c));
		}
		
		return new ResponseEntity(resultDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<ClassDTO> getColloquiumResult(@PathVariable Integer id){
		ColloquiumResult found = colloquiumResultService.findOne(id);
		if(found == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new ColloquiumResultDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ClassDTO> saveColloquiumResult(@RequestBody ColloquiumResultDTO colloquiumResult){
		ColloquiumResult newColloquiumResult = new ColloquiumResult();
		newColloquiumResult.setPoints(colloquiumResult.getPoints());
		
		if(colloquiumResult.getColloquiumId() == null || colloquiumResult.getStudentId() == null ||
				colloquiumResult.getDocumentId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Colloquium colloquium = colloquiumService.findOne(colloquiumResult.getColloquiumId().getId());
		Student student = studentService.findOne(colloquiumResult.getStudentId().getId());
		StudentDocument studDoc = studentDocumentService.findOne(colloquiumResult.getDocumentId().getId());
		
		if(colloquium == null || student == null || studDoc == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newColloquiumResult.setColloquiumId(colloquium);
		newColloquiumResult.setStudentId(student);
		newColloquiumResult.setDocumentId(studDoc);
		
		colloquiumResultService.save(newColloquiumResult);
		return new ResponseEntity<>(new ColloquiumResultDTO(newColloquiumResult), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<ClassDTO> updateColloquiumResult(@RequestBody ColloquiumResultDTO colloquiumResult){
		ColloquiumResult found = colloquiumResultService.findOne(colloquiumResult.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setPoints(colloquiumResult.getPoints());
		
		if(colloquiumResult.getDocumentId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		StudentDocument studDoc = studentDocumentService.findOne(colloquiumResult.getDocumentId().getId());
		
		if(studDoc == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setDocumentId(studDoc);
		
		// not allowed to change colloquium and student
		
		colloquiumResultService.save(found);
		return new ResponseEntity<>(new ColloquiumResultDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteClass(@PathVariable Integer id){
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
