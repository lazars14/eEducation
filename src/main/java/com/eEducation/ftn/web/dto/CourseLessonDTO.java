package com.eEducation.ftn.web.dto;

public class CourseLessonDTO {

	private Integer id;
	private String name;
	private String description;
	private CourseDTO courseId;

	public CourseLessonDTO() {
	}

	public CourseLessonDTO(CourseLesson cl) {
		super();
		this.id = cl.getId();
		this.name = cl.getName();
		this.description = cl.getDescription();
		this.courseId = new CourseDTO(cl.getCourseId());
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

	public CourseDTO getCourseId() {
		return courseId;
	}

	public void setCourseId(CourseDTO courseId) {
		this.courseId = courseId;
	}

}
