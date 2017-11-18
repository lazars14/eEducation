package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.Grade;

public class GradeDTO {
	
	private Integer id;
	private Float points;
    private Integer grade;
    private Course courseId;
    private Student studentId;
	
	public GradeDTO() {}

	public GradeDTO(Grade grade) {
		this.id = grade.getId();
		this.points = grade.getPoints();
		this.grade = grade.getGrade();
		this.courseId = grade.getCourseId();
		this.studentId = grade.getStudentId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}

	public Student getStudentId() {
		return studentId;
	}

	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}

	public Float getPoints() {
		return points;
	}

	public void setPoints(Float points) {
		this.points = points;
	}
}
