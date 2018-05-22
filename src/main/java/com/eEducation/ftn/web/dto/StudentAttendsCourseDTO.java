package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.StudentAttendsCourse;

public class StudentAttendsCourseDTO {
	
	private Integer id;
    private StudentDTO student;
    private CourseDTO course;
	
	public StudentAttendsCourseDTO() {}

	public StudentAttendsCourseDTO(StudentAttendsCourse sac) {
		this.id = sac.getId();
		this.student = new StudentDTO(sac.getStudent());
		this.course = new CourseDTO(sac.getCourse());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}
}
