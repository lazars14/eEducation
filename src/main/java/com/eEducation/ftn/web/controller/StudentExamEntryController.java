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

import com.eEducation.ftn.service.StudentExamEntryService;
import com.eEducation.ftn.web.dto.StudentExamEntryDTO;

@RestController
@RequestMapping(value="api/examEntries")
public class ClassController {
	@Autowired
	StudentExamEntryService examEntryService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	ExamPeriodService examPeriodService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentExamEntryDTO>> getAll(){
		List<StudentExamEntry> examEntries = examEntryService.findAll();
		List<StudentExamEntryDTO> examEntriesDTOs = new ArrayList<>();
		
		for(StudentExamEntry ee : examEntries){
			examEntriesDTOs.add(new StudentExamEntryDTO(ee));
		}
		
		return new ResponseEntity(examEntriesDTOs, HttpStatus.OK);
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
		newExamEntry.setEDate(classs.getName());
		
		if(examEntry.getStudent() == null || examEntry.getCourse() == null || examEntry.getExamPeriod() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student student = studentService.findOne(examEntry.getStudent().getId());
		Course course = courseService.findOne(examEntry.getCourse().getId());
		ExamPeriod examPeriod = examPeriodService.findOne(examEntry.getExamPeriod().getId());
		
		if(student == null || course == null || examPeriod == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newExamEntry.setStudent(student);
		newExamEntry.setCourse(course);
		newExamEntry.setExamPeriod(examPeriod);
		
		examEntryService.save(newExamEntry);
		return new ResponseEntity<>(new StudentExamEntry(newExamEntry), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<StudentExamEntryDTO> update(@RequestBody StudentExamEntryDTO examEntry){
		StudentExamEntry found = examEntryService.findOne(examEntry.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setEDate(classs.getName());
		
		// not allowed to change examPeriod or student
		
		if(examEntry.getCourse() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Course course = courseService.findOne(examEntry.getCourse().getId());
		
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setCourse(course);
		
		examEntryService.save(found);
		return new ResponseEntity<>(new StudentExamEntry(found), HttpStatus.OK);
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
