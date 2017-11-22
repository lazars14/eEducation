package com.eEducation.ftn.web.dto;

public class TeacherTeachesCourseDTO {
	
	private Integer id;
    private TeacherDTO teacherId;
    private CourseDTO courseId;
	
	public TeacherTeachesCourseDTO() {}

	public TeacherTeachesCourseDTO(Integer id, TeacherDTO teacherId,
			CourseDTO courseId) {
		super();
		this.id = id;
		this.teacherId = teacherId;
		this.courseId = courseId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TeacherDTO getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(TeacherDTO teacherId) {
		this.teacherId = teacherId;
	}

	public CourseDTO getCourseId() {
		return courseId;
	}

	public void setCourseId(CourseDTO courseId) {
		this.courseId = courseId;
	}
}
