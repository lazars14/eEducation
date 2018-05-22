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

import com.eEducation.ftn.service.ClassService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.StudentDTO;

@RestController
@RequestMapping(value="api/students")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	ClassService classService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getAll(){
		List<Student> students = studentService.findAll();
		List<StudentDTO> studentDTOs = new ArrayList<>();
		
		for(Student s : students) {
			studentDTOs.add(new StudentDTO(s));
		}
		
		return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<StudentDTO> getById(@PathVariable Integer id){
		Student found = studentService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<>(new StudentDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO student){
		Student newStudent = new Student();
		
		if(student.getEmail() == null || student.getSPassword() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student existing = studentRepository.findByEmail(student.getEmail());
		if(existing != null) {
			return new ResponseEntity<>(HttpStatus.ALREADY_EXISTS);
		}
		
		if(student.getClass() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Class classs = classService.findOne(student.getClass().getId());
		if(classs == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newStudent.setClass(classs);
		
		if(student.getIndexNumber() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student found = studentRepository.findByIndexNumber(student.getIndexNumber());
		if(found != null) {
			return new ResponseEntity<>(HttpStatus.ALREADY_EXISTS);
		}
		
		newStudent.setIndexNumber(student.getIndexNumber());
		newStudent.setFirstname(student.getFirstname());
		newStudent.setLastname(student.getLastname());
		
		if(student.getAccountNumber() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student foundAccNum = studentRepository.findByAccountNumber(student.getAccountNumber());
		if(foundAccNum != null) {
			return new ResponseEntity<>(HttpStatus.ALREADY_EXISTS);
		}
		
		newStudent.setStudYear(student.getStudYear());
		newStudent.setStudYearOrdNum(student.getStudYearOrdNum());
		newStudent.setEmail(student.getEmail());
		newStudent.setSPassword(student.getSPassword());
		newStudent.setEspbPoints(student.getEspbPoints());
		
		studentService.save(newStudent);
		return new ResponseEntity<>(new StudentDTO(newStudent), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<StudentDTO> update(@RequestBody StudentDTO student){
		Student found = studentService.findOne(student.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(student.getEmail() == null || student.getSPassword() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student existing = studentRepository.findByEmail(student.getEmail());
		if(existing != null && student.getId() != existing.getId()) {
			return new ResponseEntity<>(HttpStatus.ALREADY_EXISTS);
		}
		
		if(student.getClass() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Class classs = classService.findOne(student.getClass().getId());
		if(classs == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setClass(classs);
		
		if(student.getIndexNumber() == null ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student foundIndNum = studentRepository.findByIndexNumber(student.getIndexNumber());
		// found a student with that index number, not allowed
		if(foundIndNum != null && student.getId() != foundIndNum.getId()) {
			return new ResponseEntity<>(HttpStatus.ALREADY_EXISTS);
		}
		
		found.setIndexNumber(student.getIndexNumber());
		found.setFirstname(student.getFirstname());
		found.setLastname(student.getLastname());
		
		if(student.getAccountNumber() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student foundAccNum = studentRepository.findByAccountNumber(student.getAccountNumber());
		if(foundAccNum != null && student.getId() != foundAccNum.getId()) {
			return new ResponseEntity<>(HttpStatus.ALREADY_EXISTS);
		}
		
		found.setStudYear(student.getStudYear());
		found.setStudYearOrdNum(student.getStudYearOrdNum());
		found.setEmail(student.getEmail());
		found.setSPassword(student.getSPassword());
		found.setEspbPoints(student.getEspbPoints());
		
		studentService.save(found);
		return new ResponseEntity<>(new StudentDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		Student found = studentService.findOne(id);
		if(found != null) {
			studentService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
