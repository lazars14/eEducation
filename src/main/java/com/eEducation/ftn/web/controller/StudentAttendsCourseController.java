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

import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.StudentAttendsCourseService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.StudentAttendsCourseDTO;

@RestController
@RequestMapping(value="api/studentAttendsCourse")
public class StudentAttendsCourseController {
	@Autowired
	StudentAttendsCourseService sacService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentAttendsCourseDTO>> getSacs(){
		List<StudentAttendsCourse> sacS = sacService.findAll();
		List<StudentAttendsCourseDTO> sacDTOs = new ArrayList<>();
		
		for(StudentAttendsCourse sac : sacS) {
			sacDTOs.add(new StudentAttendsCourseDTO(sac));
		}
		
		return new ResponseEntity<>(sacDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<StudentAttendsCourseDTO> getSat(@PathVariable Integer id){
		StudentAttendsCourse found = sacService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new StudentAttendsCourseDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<StudentAttendsCourseDTO> saveSac(@RequestBody StudentAttendsCourseDTO sac){
		StudentAttendsCourse newSac = new StudentAttendsCourse();
		
		if(sac.getStudentId() == null || sac.getCourseId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student student = studentService.findOne(sac.getStudentId().getId());
		Course course = courseService.findOne(sac.getCourseId().getId());
		
		if(student == null || course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newSac.setStudentId(student);
		newSac.setCourseId(course);
		
		sacService.save(newSac);
		return new ResponseEntity<>(new StudentAttendsCourseDTO(newSac), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<StudentAttendsCourseDTO> updateSac(@RequestBody StudentAttendsCourseDTO sac){
		StudentAttendsCourse found = sacService.findOne(sac.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(sac.getStudentId() == null || sac.getCourseId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student student = studentService.findOne(sac.getStudentId().getId());
		Course course = courseService.findOne(sac.getCourseId().getId());
		
		if(student == null || course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setStudentId(student);
		found.setCourseId(course);
		
		sacService.save(found);
		return new ResponseEntity<>(new StudentAttendsCourseDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSac(@PathVariable Integer id){
		StudentAttendsCourse found = sacService.findOne(id);
		if(found != null) {
			sacService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
	
	
	
	
}