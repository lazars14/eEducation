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
import com.eEducation.ftn.model.Grade;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.repository.GradeRepository;
import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.GradeService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.GradeDTO;

@RestController
@RequestMapping(value="api/grades")
public class GradeController {
	@Autowired
	GradeService gradeService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	GradeRepository gradeRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<GradeDTO>> getAll(){
		List<Grade> grades = gradeService.findAll();
		List<GradeDTO> gradeDTOs = new ArrayList<>();
		
		for(Grade g : grades) {
			gradeDTOs.add(new GradeDTO(g));
		}
		
		return new ResponseEntity<>(gradeDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<GradeDTO> getById(@PathVariable Long id){
		Grade found = gradeService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new GradeDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<GradeDTO> add(@RequestBody GradeDTO grade){
		Grade newGrade = new Grade();
		newGrade.setPoints(grade.getPoints());
		newGrade.setGrade(grade.getGrade());
		
		if(grade.getCourse() == null || grade.getStudent() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Course course = courseService.findOne(grade.getCourse().getId());
		Student student = studentService.findOne(grade.getStudent().getId());
		
		if(course == null || student == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newGrade.setCourse(course);
		newGrade.setStudent(student);
		
		gradeService.save(newGrade);
		return new ResponseEntity<>(new GradeDTO(newGrade), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	public ResponseEntity<GradeDTO> update(@RequestBody GradeDTO grade){
		Grade found = gradeService.findOne(grade.getId());
		found.setPoints(grade.getPoints());
		found.setGrade(grade.getGrade());
		
		// not allowed to change course or student
		
		gradeService.save(found);
		return new ResponseEntity<>(new GradeDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Grade found = gradeService.findOne(id);
		if(found != null) {
			try {
				gradeService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/students/{studentId}")
	public ResponseEntity<List<GradeDTO>> getByStudentId(@PathVariable Long studentId){
		Student student = studentService.findOne(studentId);
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Grade> grades = gradeRepository.findByStudent(student);
		List<GradeDTO> gradeDTOs = new ArrayList<>();
		
		for(Grade g : grades) {
			gradeDTOs.add(new GradeDTO(g));
		}
		
		return new ResponseEntity<>(gradeDTOs, HttpStatus.OK);
	}

}
