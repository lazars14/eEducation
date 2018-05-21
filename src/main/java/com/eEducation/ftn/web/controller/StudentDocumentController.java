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

import com.eEducation.ftn.service.StudentDocumentService;
import com.eEducation.ftn.web.dto.StudentDocumentDTO;

@RestController
@RequestMapping(value="api/studentDocuments")
public class StudentDocumentController {
	@Autowired
	StudentDocumentService studentDocumentService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentDocumentDTO>> getAll(){
		List<StudentDocument> studentDocuments = studentDocumentService.findAll();
		List<StudentDocumentDTO> StudentDocumentDTOs = new ArrayList<>();
		
		for(StudentDocument sd : studentDocuments) {
			studentDocumentDTOs.add(new StudentDocumentDTO(sd));
		}
		
		return new ResponseEntity<>(studentDocumentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<StudentDocumentDTO> getById(@PathVariable Integer id){
		StudentDocument found = studentDocumentService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<>(new StudentDocumentDTO(found), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<StudentDocumentDTO> save(@RequestBody StudentDocumentDTO studentDocument){
		StudentDocument newStudentDocument = new StudentDocument();
		
		if(studentDocument.getCourse() == null || studentDocument.getStudent() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Course course = courseService.findOne(studentDocument.getCourse().getId());
		Student student = studentService.findOne(studentDocument.getStudent().getId());
		if(course == null || student == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newStudentDocument.setCourse(course);
		newStudentDocument.setStudent(student);
		newStudentDocument.setDocumentName(studentDocument.getDocumentName());
		newStudentDocument.setDocumentType(studentDocument.getDocumentType());
		newStudentDocument.setDocumentUrl(studentDocument.getDocumentUrl());
		newStudentDocument.setMimeType(studentDocument.getEspbPoints());
		
		studentDocumentService.save(newStudentDocument);
		return new ResponseEntity<>(new StudentDocumentDTO(newStudentDocument), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<StudentDocumentDTO> update(@RequestBody StudentDocumentDTO studentDocument){
		StudentDocument found = studentDocumentService.findOne(studentDocument.getId());
		
		// not allowed to change course and student
		
		found.setDocumentName(studentDocument.getDocumentName());
		found.setDocumentType(studentDocument.getDocumentType());
		found.setDocumentUrl(studentDocument.getDocumentUrl());
		found.setMimeType(studentDocument.getEspbPoints());
		
		studentDocumentService.save(found);
		return new ResponseEntity<>(new StudentDocumentDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		StudentDocument found = studentDocumentService.findOne(id);
		if(found != null) {
			studentDocumentService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
