package com.eEducation.ftn.web.controller;

import java.io.FileNotFoundException;
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

import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.FileService;
import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.CourseFile;
import com.eEducation.ftn.model.CourseLesson;
import com.eEducation.ftn.repository.CourseFileRepository;
import com.eEducation.ftn.repository.CourseLessonRepository;
import com.eEducation.ftn.service.CourseFileService;
import com.eEducation.ftn.service.CourseLessonService;
import com.eEducation.ftn.web.dto.CourseLessonDTO;

@RestController
@RequestMapping(value="api/course/{courseId}/courseLessons")
public class CourseLessonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CourseLessonController.class);
	
	@Autowired
	CourseLessonService courseLessonService;
	
	@Autowired
	CourseLessonRepository courseLessonRepository;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	CourseFileService courseFileService;
	
	@Autowired
	CourseFileRepository courseFileRepository;
	
	@Autowired
	FileService fileService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CourseLessonDTO>> getAll(@PathVariable Long courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<CourseLesson> courseLessons = courseLessonService.findAll();
		List<CourseLessonDTO> courseLessonDTOs = new ArrayList<>();
		
		for(CourseLesson cl : courseLessons) {
			courseLessonDTOs.add(new CourseLessonDTO(cl));
		}
		
		return new ResponseEntity<>(courseLessonDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<CourseLessonDTO> getById(@PathVariable Long id, @PathVariable Long courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseLesson found = courseLessonService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<>(new CourseLessonDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CourseLessonDTO> add(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam(value="file", required=false) List<MultipartFile> files, @PathVariable Long courseId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseLesson newCourseLesson = new CourseLesson();
		
		newCourseLesson.setCourse(course);
		newCourseLesson.setName(name);
		newCourseLesson.setDescription(description);
		
		courseLessonService.save(newCourseLesson);
		
		// add files
		for(MultipartFile file : files) {
		// upload file
			String[] uploadResult = fileService.upload(file);
					
			if(uploadResult[0].equals("invalid")) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
					
			// create course file
			CourseFile courseFile = new CourseFile();
			courseFile.setCourse(course);
			courseFile.setCourseLesson(newCourseLesson);
			courseFile.setDocumentName(uploadResult[1]);
			courseFile.setDocumentType("notification");
			courseFile.setMimeType(uploadResult[0]);
			courseFile.setDocumentURL(fileService.getFolderPath() + file.getOriginalFilename());
					
			courseFileService.save(courseFile);
		}		
		
		return new ResponseEntity<>(new CourseLessonDTO(newCourseLesson), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	public ResponseEntity<CourseLessonDTO> update(@RequestBody CourseLessonDTO courseLesson, @PathVariable Long courseId, @PathVariable Long id){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseLesson found = courseLessonService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setName(courseLesson.getName());
		found.setDescription(courseLesson.getDescription());
		
		courseLessonService.save(found);
		return new ResponseEntity<>(new CourseLessonDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseLesson found = courseLessonService.findOne(id);
		if(found != null) {
			try {
				// first remove all the files for the course
				List<CourseFile> courseFiles = courseFileRepository.findByCourseLesson(found);
				
				for(CourseFile file : courseFiles) {
					// remove from storage
					boolean deleted = false;
					try {
						deleted = fileService.delete(file.getDocumentName());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(deleted == false) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					
					// remove course file
					courseFileService.remove(file.getId());
				}
				
				// then remove the course lesson
				courseLessonService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/byCourse")
	public ResponseEntity<List<CourseLessonDTO>> getByCourse(@PathVariable Long courseId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<CourseLesson> courseLessons = courseLessonRepository.findByCourse(course);
		List<CourseLessonDTO> courseLessonDTOs = new ArrayList<>();
		
		for(CourseLesson cl : courseLessons) {
			courseLessonDTOs.add(new CourseLessonDTO(cl));
		}
		
		return new ResponseEntity<>(courseLessonDTOs, HttpStatus.OK);
	}
}
