package com.eEducation.ftn.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.CourseFile;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.StudentDocument;
import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.FileService;
import com.eEducation.ftn.service.StudentDocumentService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.StudentDocumentDTO;

@RestController
@RequestMapping(value="api/student/{studentId}/studentDocuments")
public class StudentDocumentController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentDocumentController.class);
	
	@Autowired
	StudentDocumentService studentDocumentService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	FileService fileService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentDocumentDTO>> getAll(@PathVariable Long courseId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<StudentDocument> studentDocuments = studentDocumentService.findAll();
		List<StudentDocumentDTO> studentDocumentDTOs = new ArrayList<>();
		
		for(StudentDocument sd : studentDocuments) {
			studentDocumentDTOs.add(new StudentDocumentDTO(sd));
		}
		
		logger.info("student document - returned all");
		
		return new ResponseEntity<>(studentDocumentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<StudentDocumentDTO> getById(@PathVariable Long id, @PathVariable Long courseId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		StudentDocument found = studentDocumentService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		logger.info("student document - found by id " + id);
		
		return new ResponseEntity<>(new StudentDocumentDTO(found), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<StudentDocumentDTO> add(@RequestBody StudentDocumentDTO studentDocument, @PathVariable Long courseId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		StudentDocument newStudentDocument = new StudentDocument();
		
		if(studentDocument.getStudent() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student student = studentService.findOne(studentDocument.getStudent().getId());
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newStudentDocument.setCourse(course);
		newStudentDocument.setStudent(student);
		newStudentDocument.setDocumentName(studentDocument.getDocumentName());
		newStudentDocument.setDocumentType(studentDocument.getDocumentType());
		newStudentDocument.setDocumentURL(studentDocument.getDocumentURL());
		newStudentDocument.setMimeType(studentDocument.getMimeType());
		
		studentDocumentService.save(newStudentDocument);
		
		logger.info("student document - adding");
		
		return new ResponseEntity<>(new StudentDocumentDTO(newStudentDocument), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	public ResponseEntity<StudentDocumentDTO> update(@RequestBody StudentDocumentDTO studentDocument, @PathVariable Long courseId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		StudentDocument found = studentDocumentService.findOne(studentDocument.getId());
		
		// not allowed to change course and student
		
		found.setDocumentName(studentDocument.getDocumentName());
		found.setDocumentType(studentDocument.getDocumentType());
		found.setDocumentURL(studentDocument.getDocumentURL());
		found.setMimeType(studentDocument.getMimeType());
		
		studentDocumentService.save(found);
		return new ResponseEntity<>(new StudentDocumentDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long courseId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		StudentDocument found = studentDocumentService.findOne(id);
		if(found != null) {
			try {
				studentDocumentService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/download/{documentId}")
	public ResponseEntity<Resource> download(@PathVariable Long studentId, @PathVariable Long documentId){
		Student student = studentService.findOne(studentId);
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		StudentDocument document = studentDocumentService.findOne(documentId);
		if(document == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(student.getId() != document.getStudent().getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		String folderPath = fileService.getFolderPath();
		
		try {
			File file = new File(folderPath + document.getDocumentName());
							
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Disposition", "attachment: " + document.getDocumentName());
			headers.set("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.set("Pragma", "no-cache");
			
			return ResponseEntity.ok()
		            .headers(headers)
		            .contentLength(file.length())
		            .contentType(MediaType.parseMediaType(document.getMimeType()))
		            .body(resource);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// collection methods
}
