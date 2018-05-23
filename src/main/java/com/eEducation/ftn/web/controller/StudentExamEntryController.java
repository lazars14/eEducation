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
import com.eEducation.ftn.model.StudentExamEntry;
import com.eEducation.ftn.service.ExamTermService;
import com.eEducation.ftn.service.StudentExamEntryService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.StudentExamEntryDTO;
import com.eEducation.ftn.service.Grade;
import com.eEducation.ftn.service.GradeService;
import com.eEducation.ftn.web.dto.GradeDTO;

@RestController
@RequestMapping(value="api/examEntries")
public class StudentExamEntryController {
	@Autowired
	StudentExamEntryService examEntryService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	ExamTermService examTermService;
	
	@Autowired
	GradeService gradeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentExamEntryDTO>> getAll(){
		List<StudentExamEntry> examEntries = examEntryService.findAll();
		List<StudentExamEntryDTO> examEntriesDTOs = new ArrayList<>();
		
		for(StudentExamEntry ee : examEntries){
			examEntriesDTOs.add(new StudentExamEntryDTO(ee));
		}
		
		return new ResponseEntity<>(examEntriesDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<StudentExamEntryDTO> getById(@PathVariable Integer id){
		StudentExamEntry found = examEntryService.findOne(id);
		if(found == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new StudentExamEntryDTO(found), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<StudentExamEntryDTO> save(@RequestBody StudentExamEntry examEntry){
		StudentExamEntry newExamEntry = new StudentExamEntry();
		
		if(examEntry.getStudent() == null || examEntry.getExamTerm() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student student = studentService.findOne(examEntry.getStudent().getId());
		ExamTerm examTerm = examTermService.findOne(examEntry.getExamTerm().getId());
		
		if(student == null || examTerm == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// grade is null when student fills in entry
		
		newExamEntry.setStudent(student);
		newExamEntry.setExamTerm(examTerm);
		
		examEntryService.save(newExamEntry);
		return new ResponseEntity<>(new StudentExamEntryDTO(newExamEntry), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<StudentExamEntryDTO> update(@RequestBody StudentExamEntryDTO examEntry){
		StudentExamEntry found = examEntryService.findOne(examEntry.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(examEntry.getStudent() == null || examEntry.getExamTerm() == null || examEntry.getGrade()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student student = studentService.findOne(examEntry.getStudent().getId());
		ExamTerm examTerm = examTermService.findOne(examEntry.getExamTerm().getId());
		// grade must be filled when teacher grades exam
		Grade grade = gradeService.findOne(examEntry.getGrade().getId());
		
		if(student == null || examTerm == null || grade == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setStudent(student);
		found.setExamTerm(examTerm);
		found.setGrade(grade);
		
		examEntryService.save(found);
		return new ResponseEntity<>(new StudentExamEntryDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		StudentExamEntry found = examEntryService.findOne(id);
		if(found != null){
			examEntryService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
