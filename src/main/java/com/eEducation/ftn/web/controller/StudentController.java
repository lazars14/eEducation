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

import com.eEducation.ftn.model.CollegeDirection;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.repository.StudentRepository;
import com.eEducation.ftn.service.CollegeDirectionService;
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
	CollegeDirectionService directionService;
	
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
	public ResponseEntity<StudentDTO> getById(@PathVariable Long id){
		Student found = studentService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		found.setSPassword("");
		
		return new ResponseEntity<>(new StudentDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<StudentDTO> add(@RequestBody StudentDTO student){
		Student newStudent = new Student();
		
		if(student.getEmail() == null || student.getsPassword() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student existing = studentRepository.findByEmail(student.getEmail());
		if(existing != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(student.getClass() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		CollegeDirection direction = directionService.findOne(student.getCollegeDirection().getId());
		if(direction == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newStudent.setCollegeDirection(direction);
		
		if(student.getIndexNumber() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student found = studentRepository.findByIndexNumber(student.getIndexNumber());
		if(found != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		newStudent.setIndexNumber(student.getIndexNumber());
		newStudent.setFirstname(student.getFirstname());
		newStudent.setLastname(student.getLastname());
		
		if(student.getAccountNumber() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student foundAccNum = studentRepository.findByAccountNumber(student.getAccountNumber());
		if(foundAccNum != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		Student foundRefNum = studentRepository.findByReferenceNumber(student.getReferenceNumber());
		if(foundRefNum != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		newStudent.setAccountNumber(student.getAccountNumber());
		newStudent.setReferenceNumber(student.getReferenceNumber());
		newStudent.setStudYear(student.getStudYear());
		newStudent.setStudYearOrdNum(student.getStudYearOrdNum());
		newStudent.setEmail(student.getEmail());
		// encode password
		newStudent.setSPassword(student.getsPassword());
		newStudent.setEspbPoints(student.getEspbPoints());
		
		studentService.save(newStudent);
		newStudent.setSPassword("");
		return new ResponseEntity<>(new StudentDTO(newStudent), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<StudentDTO> update(@RequestBody StudentDTO student){
		Student found = studentService.findOne(student.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(student.getEmail() == null || student.getsPassword() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student existing = studentRepository.findByEmail(student.getEmail());
		if(existing != null && student.getId() != existing.getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(student.getClass() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		CollegeDirection direction = directionService.findOne(student.getCollegeDirection().getId());
		if(direction == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setCollegeDirection(direction);
		
		if(student.getIndexNumber() == null ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student foundIndNum = studentRepository.findByIndexNumber(student.getIndexNumber());
		// found a student with that index number, not allowed
		if(foundIndNum != null && student.getId() != foundIndNum.getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		found.setIndexNumber(student.getIndexNumber());
		found.setFirstname(student.getFirstname());
		found.setLastname(student.getLastname());
		
		if(student.getAccountNumber() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student foundAccNum = studentRepository.findByAccountNumber(student.getAccountNumber());
		if(foundAccNum != null && student.getId() != foundAccNum.getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		Student foundRefNum = studentRepository.findByReferenceNumber(student.getReferenceNumber());
		if(foundRefNum != null && student.getId() != foundRefNum.getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		found.setAccountNumber(student.getAccountNumber());
		found.setReferenceNumber(student.getReferenceNumber());
		found.setStudYear(student.getStudYear());
		found.setStudYearOrdNum(student.getStudYearOrdNum());
		found.setEmail(student.getEmail());
		
		
		if(student.getsPassword() != null && !student.getsPassword().equals("")) {
			// encode
			found.setSPassword(student.getsPassword());
		}
		
		
		found.setEspbPoints(student.getEspbPoints());
		
		studentService.save(found);
		return new ResponseEntity<>(new StudentDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Student found = studentService.findOne(id);
		if(found != null) {
			studentService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/class/{classId}")
	public ResponseEntity<List<StudentDTO>> getByClass(@PathVariable Long classId){
		CollegeDirection direction = directionService.findOne(classId);
		if(direction == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Student> students = studentRepository.findByDirection(direction);
		List<StudentDTO> studentDTOs = new ArrayList<>();
		
		for(Student s : students) {
			s.setSPassword("");
			studentDTOs.add(new StudentDTO(s));
		}
		
		return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
	}
}
