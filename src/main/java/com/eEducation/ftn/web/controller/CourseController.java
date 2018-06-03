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
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.StudentAttendsCourse;
import com.eEducation.ftn.model.Teacher;
import com.eEducation.ftn.model.TeacherTeachesCourse;
import com.eEducation.ftn.repository.StudentAttendsCourseRepository;
import com.eEducation.ftn.repository.TeacherTeachesCourseRepository;
import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.service.TeacherService;
import com.eEducation.ftn.web.dto.CourseDTO;

@RestController
@RequestMapping(value="api/courses")
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	TeacherTeachesCourseRepository ttcRepository;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentAttendsCourseRepository sacRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CourseDTO>> getAll(){
		List<Course> courses = courseService.findAll();
		List<CourseDTO> courseDTOs = new ArrayList<>();
		
		for(Course c : courses) {
			courseDTOs.add(new CourseDTO(c));
		}
		
		return new ResponseEntity<>(courseDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<CourseDTO> getById(@PathVariable Long id){
		Course found = courseService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<>(new CourseDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CourseDTO> add(@RequestBody CourseDTO course){
		Course newCourse = new Course();
		
		if(course.getTeacher() == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Teacher teacher = teacherService.findOne(course.getTeacher().getId());
		
		if(teacher == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
				
		newCourse.setTeacher(teacher);
		newCourse.setName(course.getName());
		newCourse.setDescription(course.getDescription());
		newCourse.setEspbPoints(course.getEspbPoints());
		
		courseService.save(newCourse);
		return new ResponseEntity<>(new CourseDTO(newCourse), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<CourseDTO> update(@RequestBody CourseDTO course){
		Course found = courseService.findOne(course.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(course.getTeacher() == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Teacher teacher = teacherService.findOne(course.getTeacher().getId());
		
		if(teacher == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
				
		found.setTeacher(teacher);
		found.setName(course.getName());
		found.setDescription(course.getDescription());
		found.setEspbPoints(course.getEspbPoints());
		
		courseService.save(found);
		return new ResponseEntity<>(new CourseDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Course found = courseService.findOne(id);
		if(found != null) {
			courseService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/teacher/{teacherId}")
	public ResponseEntity<List<CourseDTO>> getByTeacher(@PathVariable Long teacherId){
		Teacher teacher = teacherService.findOne(teacherId);
		if(teacher == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<TeacherTeachesCourse> ttcS = ttcRepository.findByTeacher(teacher);
		List<CourseDTO> courseDTOs = new ArrayList<>();
		
		for(TeacherTeachesCourse t : ttcS) {
			courseDTOs.add(new CourseDTO(t.getCourse()));
		}
		
		return new ResponseEntity<>(courseDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/student/{studentId}")
	public ResponseEntity<List<CourseDTO>> getByStudent(@PathVariable Long studentId){
		Student student = studentService.findOne(studentId);
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<StudentAttendsCourse> sacS = sacRepository.findByStudent(student);
		List<CourseDTO> courseDTOs = new ArrayList<>();
		
		for(StudentAttendsCourse s : sacS) {
			courseDTOs.add(new CourseDTO(s.getCourse()));
		}
		
		return new ResponseEntity<>(courseDTOs, HttpStatus.OK);
	}
}
