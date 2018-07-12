package com.eEducation.ftn.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eEducation.ftn.model.Colloquium;
import com.eEducation.ftn.model.ColloquiumResult;
import com.eEducation.ftn.model.CourseFile;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.StudentDocument;
import com.eEducation.ftn.repository.ColloquiumResultRepository;
import com.eEducation.ftn.service.ColloquiumResultService;
import com.eEducation.ftn.service.ColloquiumService;
import com.eEducation.ftn.service.FileService;
import com.eEducation.ftn.service.StudentDocumentService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.ColloquiumResultDTO;

@RestController
@RequestMapping(value="api/colloquium/{colloquiumId}/colloquiumResults")
public class ColloquiumResultController {
	
	private static final Logger logger = LoggerFactory.getLogger(ColloquiumResultController.class);
	
	@Autowired
	ColloquiumResultService colloquiumResultService;
	
	@Autowired
	ColloquiumResultRepository colloquiumResultRepository;
	
	@Autowired
	ColloquiumService colloquiumService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentDocumentService studentDocumentService;
	
	@Autowired
	FileService fileService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ColloquiumResultDTO>> getAll(@PathVariable Long colloquiumId){
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
	public ResponseEntity<ColloquiumResultDTO> getById(@PathVariable Long id, @PathVariable Long colloquiumId){
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
	
	@RequestMapping(method = RequestMethod.GET, value="/byColloquium")
	public ResponseEntity<List<ColloquiumResultDTO>> getByColloquium(@PathVariable Long colloquiumId){
		Colloquium colloquium = colloquiumService.findOne(colloquiumId);
		
		if(colloquium == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<ColloquiumResult> results = colloquiumResultRepository.findByColloquium(colloquium);
		List<ColloquiumResultDTO> resultDTOs = new ArrayList<>();
		
		for(ColloquiumResult result : results) {
			resultDTOs.add(new ColloquiumResultDTO(result));
		}
				
		return new ResponseEntity<>(resultDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/student/{studentId}")
	public ResponseEntity<ColloquiumResultDTO> getByStudentAndColloquium(@PathVariable Long studentId, @PathVariable Long colloquiumId){
		Colloquium colloquium = colloquiumService.findOne(colloquiumId);
		
		if(colloquium == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student student = studentService.findOne(studentId);
		
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		ColloquiumResult found = colloquiumResultRepository.findByStudentAndColloquium(student, colloquium);
		if(found == null){
			return new ResponseEntity<>(new ColloquiumResultDTO(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new ColloquiumResultDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ColloquiumResultDTO> add(@RequestParam("studentId") String studentId, 
			@RequestParam("file") MultipartFile file, @PathVariable Long colloquiumId){
		Colloquium colloquium = colloquiumService.findOne(colloquiumId);	
		if(colloquium == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student student = studentService.findOne(Long.parseLong(studentId));
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		StudentDocument studDoc = null;
		if(file.isEmpty() == false) {
			// upload file
			String[] uploadResult = fileService.upload(file);
			
			if(uploadResult[0].equals("invalid")) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			studDoc = new StudentDocument();
			studDoc.setCourse(colloquium.getCourse());
			studDoc.setStudent(student);
			studDoc.setDocumentName(uploadResult[1]);
			studDoc.setDocumentType("notification");
			studDoc.setMimeType(uploadResult[0]);
			studDoc.setDocumentURL(fileService.getFolderPath() + file.getOriginalFilename());
			
			studentDocumentService.save(studDoc);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		ColloquiumResult newColloquiumResult = new ColloquiumResult();
		newColloquiumResult.setPoints(Float.valueOf(0));
		newColloquiumResult.setColloquium(colloquium);
		newColloquiumResult.setStudent(student);
		newColloquiumResult.setDocument(studDoc);
		
		colloquiumResultService.save(newColloquiumResult);
		return new ResponseEntity<>(new ColloquiumResultDTO(newColloquiumResult), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<ColloquiumResultDTO> update(@RequestBody ColloquiumResultDTO colloquiumResult, @PathVariable Long colloquiumId){
		Colloquium colloquium = colloquiumService.findOne(colloquiumId);
		
		if(colloquium == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		ColloquiumResult found = colloquiumResultService.findOne(colloquiumResult.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setPoints(colloquiumResult.getPoints());
		
		colloquiumResultService.save(found);
		return new ResponseEntity<>(new ColloquiumResultDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long colloquiumId){
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
