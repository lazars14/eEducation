package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Grade;

public class GradeDTO {
	
	private Long id;
	private Float points;
    private Integer grade;
    private CourseDTO course;
    private StudentDTO student;
	
	public GradeDTO() {}

	public GradeDTO(Grade grade) {
		this.id = grade.getId();
		this.points = grade.getPoints();
		this.grade = grade.getGrade();
		this.course = new CourseDTO(grade.getCourse());
		this.student = new StudentDTO(grade.getStudent());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public Float getPoints() {
		return points;
	}

	public void setPoints(Float points) {
		this.points = points;
	}
}
