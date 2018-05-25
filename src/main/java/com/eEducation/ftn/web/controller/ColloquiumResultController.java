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

import com.eEducation.ftn.model.Colloquium;
import com.eEducation.ftn.model.ColloquiumResult;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.StudentDocument;
import com.eEducation.ftn.service.ColloquiumResultService;
import com.eEducation.ftn.service.ColloquiumService;
import com.eEducation.ftn.service.StudentDocumentService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.ColloquiumResultDTO;

@RestController
@RequestMapping(value="api/colloquium/{colloquiumId}/colloquiumResults")
public class ColloquiumResultController {
	@Autowired
	ColloquiumResultService colloquiumResultService;
	
	@Autowired
	ColloquiumService colloquiumService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentDocumentService studentDocumentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ColloquiumResultDTO>> getAll(@PathVariable Integer colloquiumId){
		Colloquium colloquium = colloquiumService.findOne(colloquiumId);
		
		if(colloquium == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<ColloquiumResult> results = colloquiumResultService.findAll();
		List<ColloquiumResultDTO> resultDTOs = new ArrayList<>();
		
		for(ColloquiumResult c : results){
			resultDTOs.add(new ColloquiumResultDTO(c));
		}
		
		return new ResponseEntity<>(resultDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<ColloquiumResultDTO> getById(@PathVariable Integer id, @PathVariable Integer colloquiumId){
		Colloquium colloquium = colloquiumService.findOne(colloquiumId);
		
		if(colloquium == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		ColloquiumResult found = colloquiumResultService.findOne(id);
		if(found == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new ColloquiumResultDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ColloquiumResultDTO> add(@RequestBody ColloquiumResultDTO colloquiumResult, @PathVariable Integer colloquiumId){
		Colloquium colloquium = colloquiumService.findOne(colloquiumId);
		
		if(colloquium == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		ColloquiumResult newColloquiumResult = new ColloquiumResult();
		newColloquiumResult.setPoints(colloquiumResult.getPoints());
		
		if(colloquiumResult.getColloquium() == null || colloquiumResult.getStudent() == null ||
				colloquiumResult.getDocument() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student student = studentService.findOne(colloquiumResult.getStudent().getId());
		StudentDocument studDoc = studentDocumentService.findOne(colloquiumResult.getDocument().getId());
		
		if(student == null || studDoc == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newColloquiumResult.setColloquium(colloquium);
		newColloquiumResult.setStudent(student);
		newColloquiumResult.setDocument(studDoc);
		
		colloquiumResultService.save(newColloquiumResult);
		return new ResponseEntity<>(new ColloquiumResultDTO(newColloquiumResult), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<ColloquiumResultDTO> update(@RequestBody ColloquiumResultDTO colloquiumResult, @PathVariable Integer colloquiumId){
		Colloquium colloquium = colloquiumService.findOne(colloquiumId);
		
		if(colloquium == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		ColloquiumResult found = colloquiumResultService.findOne(colloquiumResult.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setPoints(colloquiumResult.getPoints());
		
		if(colloquiumResult.getDocument() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		StudentDocument studDoc = studentDocumentService.findOne(colloquiumResult.getDocument().getId());
		
		if(studDoc == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setDocument(studDoc);
		
		// not allowed to change colloquium and student
		
		colloquiumResultService.save(found);
		return new ResponseEntity<>(new ColloquiumResultDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id, @PathVariable Integer colloquiumId){
		Colloquium colloquium = colloquiumService.findOne(colloquiumId);
		
		if(colloquium == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		ColloquiumResult found = colloquiumResultService.findOne(id);
		if(found != null){
			colloquiumResultService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
