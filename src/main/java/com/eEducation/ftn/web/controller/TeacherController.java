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

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.Rank;
import com.eEducation.ftn.model.Teacher;
import com.eEducation.ftn.model.TeacherTeachesCourse;
import com.eEducation.ftn.repository.TeacherRepository;
import com.eEducation.ftn.repository.TeacherTeachesCourseRepository;
import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.RankService;
import com.eEducation.ftn.service.TeacherService;
import com.eEducation.ftn.web.dto.TeacherDTO;

@RestController
@RequestMapping(value="api/teachers")
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	RankService rankService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	TeacherTeachesCourseRepository ttcRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TeacherDTO>> getAll(){
		List<Teacher> teachers = teacherService.findAll();
		List<TeacherDTO> teacherDTOs = new ArrayList<>();
		
		for(Teacher t : teachers) {
			t.setSPassword("");
			teacherDTOs.add(new TeacherDTO(t));
		}
		
		return new ResponseEntity<>(teacherDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<TeacherDTO> getById(@PathVariable Long id){
		Teacher found = teacherService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		found.setSPassword("");
		
		return new ResponseEntity<>(new TeacherDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<TeacherDTO> add(@RequestBody TeacherDTO teacher){
		Teacher newTeacher = new Teacher();
		newTeacher.setFirstname(teacher.getFirstname());
		newTeacher.setLastname(teacher.getLastname());
		
		if(teacher.getEmail() == null || teacher.getsPassword() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Teacher existing = teacherRepository.findByEmail(teacher.getEmail());
		if(existing != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		newTeacher.setEmail(teacher.getEmail());
		// encode password
		newTeacher.setSPassword(teacher.getsPassword());
		
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
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	public ResponseEntity<TeacherDTO> update(@RequestBody TeacherDTO teacher){
		Teacher found = teacherService.findOne(teacher.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setFirstname(teacher.getFirstname());
		found.setLastname(teacher.getLastname());
		
		if(teacher.getEmail() == null || teacher.getsPassword() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Teacher existing = teacherRepository.findByEmail(teacher.getEmail());
		if(existing != null && teacher.getId() != existing.getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		found.setEmail(teacher.getEmail());
		
		if(teacher.getsPassword() != null && !teacher.getsPassword().equals("")) {
			// encode
			found.setSPassword(teacher.getsPassword());
		}

		
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
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Teacher found = teacherService.findOne(id);
		if(found != null) {
			teacherService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/course/{courseId}")
	public ResponseEntity<List<TeacherDTO>> getByCourse(@PathVariable Long courseId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<TeacherTeachesCourse> ttcS = ttcRepository.findByCourse(course);
		List<TeacherDTO> teacherDTOs = new ArrayList<>();
		
		for(TeacherTeachesCourse t : ttcS) {
			t.getTeacher().setSPassword("");
			teacherDTOs.add(new TeacherDTO(t.getTeacher()));
		}
		
		return new ResponseEntity<>(teacherDTOs, HttpStatus.OK);
	}
}
