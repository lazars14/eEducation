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

import com.eEducation.ftn.model.ExamTerm;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.ExamPeriod;
import com.eEducation.ftn.service.ExamTermService;
import com.eEducation.ftn.service.ExamPeriodService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.ExamTermDTO;

@RestController
@RequestMapping(value="api/examTerms")
public class ExamTermController {
	@Autowired
	ExamTermService examTermService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	ExamPeriodService examPeriodService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ExamTermDTO>> getAll(){
		List<ExamTermCourse> examTerms = examTermService.findAll();
		List<ExamTermDTO> examTermDTOs = new ArrayList<>();
		
		for(ExamTerm e : examTerms) {
			examTermDTOs.add(new ExamTermDTO(e));
		}
		
		return new ResponseEntity<>(examTermDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<ExamTermDTO> getById(@PathVariable Integer id){
		ExamTerm found = examTermService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new ExamTermDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ExamTermDTO> save(@RequestBody ExamTermDTO examTerm){
		ExamTerm newExamTerm = new ExamTerm();
		
		if(examTerm.getStudent() == null || examTerm.getExamPeriod() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student student = studentService.findOne(examTerm.getStudent().getId());
		ExamPeriod examPeriod = examPeriodService.findOne(examTerm.getExamPeriod().getId());
		
		if(student == null || examPeriod == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newExamTerm.setStudent(student);
		newExamTerm.setExamPeriod(examPeriod);
		
		examTermService.save(newExamTerm);
		return new ResponseEntity<>(new ExamTermDTO(newExamTerm), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<ExamTermDTO> update(@RequestBody ExamTermDTO examTerm){
		ExamTerm found = examTermService.findOne(examTerm.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(examTerm.getStudent() == null || examTerm.getExamPeriod() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student student = studentService.findOne(examTerm.getStudent().getId());
		ExamPeriod examPeriod = examPeriodService.findOne(examTerm.getExamPeriod().getId());
		
		if(student == null || examPeriod == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setStudent(student);
		found.setExamPeriod(examPeriod);
		
		examTermService.save(found);
		return new ResponseEntity<>(new ExamTermDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		ExamTerm found = examTermService.findOne(id);
		if(found != null) {
			examTermService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
	
	
	
	
}