package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.Teacher;
import com.eEducation.ftn.model.TeacherTeachesCourse;

public class TeacherTeachesCourseDTO {
	
	private Integer id;
    private Boolean deleted;
    private Teacher teacherId;
    private Course courseId;
	
	public TeacherTeachesCourseDTO() {}

	public TeacherTeachesCourseDTO(TeacherTeachesCourse ttc) {
		super();
		this.id = ttc.getId();
		this.deleted = ttc.getDeleted();
		this.teacherId = ttc.getTeacherId();
		this.courseId = ttc.getCourseId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Teacher getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Teacher teacherId) {
		this.teacherId = teacherId;
	}

	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}
}
