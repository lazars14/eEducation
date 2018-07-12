package com.eEducation.ftn.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.CourseFile;
import com.eEducation.ftn.model.CourseLesson;
import com.eEducation.ftn.repository.CourseFileRepository;
import com.eEducation.ftn.service.CourseFileService;
import com.eEducation.ftn.service.CourseLessonService;
import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.FileService;
import com.eEducation.ftn.web.dto.CourseFileDTO;

@RestController
@RequestMapping(value="api/courseFiles")
public class CourseFileController {
	
	private static final Logger logger = LoggerFactory.getLogger(CourseFileController.class);
	
	@Autowired
	CourseFileService courseFileService;
	
	@Autowired
	CourseFileRepository courseFileRepository;
	
	@Autowired
	CourseLessonService courseLessonService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	FileService fileService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CourseFileDTO>> getAll(@PathVariable Long courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<CourseFile> courseFiles = courseFileService.findAll();
		List<CourseFileDTO> courseFileDTOs = new ArrayList<>();
		
		for(CourseFile cf : courseFiles) {
			courseFileDTOs.add(new CourseFileDTO(cf));
		}
		
		return new ResponseEntity<>(courseFileDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<CourseFileDTO> getById(@PathVariable Long id, @PathVariable Long courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseFile found = courseFileService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<>(new CourseFileDTO(found), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			value="/course/{courseId}/courseLesson/{courseLessonId}")
	public ResponseEntity<Void> add(@RequestParam("file") MultipartFile[] files, @PathVariable Long courseId, 
			@PathVariable Long courseLessonId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseLesson courseLesson = courseLessonService.findOne(courseLessonId);
		if(courseLesson == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(courseLesson.getCourse().getId() != course.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		for(MultipartFile file : files) {
			// upload file
			String[] uploadResult = fileService.upload(file);
			
			if(uploadResult[0].equals("invalid")) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			// create course file
			CourseFile courseFile = new CourseFile();
			courseFile.setCourse(course);
			courseFile.setCourseLesson(courseLesson);
			courseFile.setDocumentName(uploadResult[1]);
			courseFile.setDocumentType("courseFile");
			courseFile.setMimeType(uploadResult[0]);
			courseFile.setDocumentURL(fileService.getFolderPath() + file.getOriginalFilename());
			
			courseFileService.save(courseFile);
			
			System.out.println("added course file with name " + courseFile.getDocumentName());
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	public ResponseEntity<CourseFileDTO> update(@RequestBody CourseFileDTO courseFile, @PathVariable Long courseId){
		Course c = courseService.findOne(courseId);
		if(c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseFile found = courseFileService.findOne(courseFile.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// not allowed to change courseLesson and course
		
		found.setDocumentName(courseFile.getDocumentName());
		found.setDocumentType(courseFile.getDocumentType());
		found.setDocumentURL(courseFile.getDocumentURL());
		found.setMimeType(courseFile.getMimeType());
		
		courseFileService.save(found);
		return new ResponseEntity<>(new CourseFileDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		CourseFile found = courseFileService.findOne(id);
		if(found != null) {
			// first try to delete file in file system
			boolean deleted = false;
			try {
				deleted = fileService.delete(found.getDocumentName());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(deleted == false) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			try {
				courseFileService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/courseLessons/{lessonId}")
	public ResponseEntity<List<CourseFileDTO>> getByCourseLesson(@PathVariable Long lessonId){
		CourseLesson courseLesson = courseLessonService.findOne(lessonId);
		if(courseLesson == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<CourseFile> courseFiles = courseFileRepository.findByCourseLesson(courseLesson);
		List<CourseFileDTO> courseFileDTOs = new ArrayList<>();
		
		for(CourseFile cf : courseFiles) {
			courseFileDTOs.add(new CourseFileDTO(cf));
		}
		
		return new ResponseEntity<>(courseFileDTOs, HttpStatus.OK);
	}
	
	/*
	 * This is for getting files related to notifications
	 */
	@RequestMapping(method = RequestMethod.GET, value="/courses/{courseId}/notifications")
	public ResponseEntity<List<CourseFileDTO>> getByCourseNotifications(@PathVariable Long courseId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<CourseFile> courseFiles = courseFileRepository.findByCourse(course);
		List<CourseFileDTO> courseFileDTOs = new ArrayList<>();
		
		for(CourseFile cf : courseFiles) {
			if(cf.getCourseLesson() == null) {
				courseFileDTOs.add(new CourseFileDTO(cf));
			}
		}
		
		return new ResponseEntity<>(courseFileDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/course/{courseId}/download/{courseFileId}")
	public ResponseEntity<Resource> download(@PathVariable Long courseId, @PathVariable Long courseFileId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CourseFile courseFile = courseFileService.findOne(courseFileId);
		if(courseFile == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(courseFile.getCourse().getId() != course.getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		String folderPath = fileService.getFolderPath();
		
		try {
			File file = new File(folderPath + courseFile.getDocumentName());
							
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Disposition", "attachment: " + courseFile.getDocumentName());
			headers.set("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.set("Pragma", "no-cache");
			
			return ResponseEntity.ok()
		            .headers(headers)
		            .contentLength(file.length())
		            .contentType(MediaType.parseMediaType(courseFile.getMimeType()))
		            .body(resource);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
