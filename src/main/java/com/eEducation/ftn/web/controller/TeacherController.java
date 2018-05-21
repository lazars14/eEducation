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
import com.eEducation.ftn.service.RankService;
import com.eEducation.ftn.service.TeacherService;
import com.eEducation.ftn.web.dto.TeacherDTO;

@RestController
@RequestMapping(value="api/teachers")
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	RankService rankService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TeacherDTO>> getTeachers(){
		List<Teacher> teachers = teacherService.findAll();
		List<TeacherDTO> teacherDTOs = new ArrayList<>();
		
		for(Teacher t : teachers) {
			teacherDTOs.add(new TeacherDTO(t));
		}
		
		return new ResponseEntity<>(teacherDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<TeacherDTO> getTeacher(@PathVariable Integer id){
		Teacher found = teacherService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new TeacherDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<TeacherDTO> saveTeacher(@RequestBody TeacherDTO teacher){
		Teacher newTeacher = new Teacher();
		newTeacher.setFirstname(teacher.getFirstname());
		newTeacher.setLastname(teacher.getLastname());
		newTeacher.setEmail(teacher.getEmail());
		newTeacher.setSPassword(teacher.getSPassword());
		
		if(teacher.getRank() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Rank rank = rankService.findOne(teacher.getRank().getId());
		if(rank == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newTeacher.setRank(rank);
		
		teacherService.save(newTeacher);
		return new ResponseEntity<>(new TeacherDTO(newTeacher), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacher){
		Teacher found = teacherService.findOne(teacher.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setFirstname(teacher.getFirstname());
		found.setLastname(teacher.getLastname());
		found.setEmail(teacher.getEmail());
		found.setSPassword(teacher.getSPassword());
		
		if(teacher.getRank() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Rank rank = rankService.findOne(teacher.getRank().getId());
		if(rank == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setRank(rank);
		
		teacherService.save(found);
		return new ResponseEntity<>(new TeacherDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTeacher(@PathVariable Integer id){
		Teacher found = teacherService.findOne(id);
		if(found != null) {
			teacherService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
