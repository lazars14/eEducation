package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.TeacherTeachesCourse;

public class TeacherTeachesCourseDTO {
	
	private Long id;
    private TeacherDTO teacher;
    private CourseDTO course;
	
	public TeacherTeachesCourseDTO() {}

	public TeacherTeachesCourseDTO(TeacherTeachesCourse ttc) {
		super();
		this.id = ttc.getId();
		this.teacher = new TeacherDTO(ttc.getTeacher());
		this.course = new CourseDTO(ttc.getCourse());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TeacherDTO getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherDTO teacher) {
		this.teacher = teacher;
	}

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}
}
