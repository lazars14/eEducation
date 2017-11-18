package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Course;

public class CourseLessonDTO {

	private Integer id;
	private String name;
	private String description;
	private Course courseId;

	public CourseLessonDTO() {
	}

	public CourseLessonDTO(Integer id, String name, String description, Course courseId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.courseId = courseId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}

}
