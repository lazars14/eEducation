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
	ClassService classService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getStudents(){
		List<Student> students = studentService.findAll();
		List<StudentDTO> studentDTOs = new ArrayList<>();
		
		for(Student s : students) {
			studentDTOs.add(new StudentDTO(s));
		}
		
		return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<StudentDTO> getStudent(@PathVariable Integer id){
		Student found = studentService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<>(new StudentDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO student){
		Student newStudent = new Student();
		
		if(student.getClassId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Class classs = classService.findOne(student.getClassId().getId());
		if(classs == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newStudent.setClassId(classs);
		
		if(student.getIndexNumber() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// find by indexNumber naci kako ide
		Student found = studentService.findByIndexNumber(student.getIndexNumber());
		if(found != null) {
			return new ResponseEntity<>(HttpStatus.ALREADY_EXISTS);
		}
		
		newStudent.setIndexNumber(student.getIndexNumber());
		newStudent.setFirstname(student.getFirstname());
		newStudent.setLastname(student.getLastname());
		
		if(student.getAccountNumber() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// find by accountNumber naci kako ide
		Student foundAccNum = studentService.findByAccountNumber(student.getAccountNumber());
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
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO student){
		Student found = studentService.findOne(student.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(student.getClassId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Class classs = classService.findOne(student.getClassId().getId());
		if(classs == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setClassId(classs);
		
		if(student.getIndexNumber() == null ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// find by indexNumber naci kako ide
		Student foundIndNum = studentService.findByIndexNumber(student.getIndexNumber());
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
		
		// find by accountNumber naci kako ide
		Student foundAccNum = studentService.findByAccountNumber(student.getAccountNumber());
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
	public ResponseEntity<Void> deleteStudent(@PathVariable Integer id){
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
