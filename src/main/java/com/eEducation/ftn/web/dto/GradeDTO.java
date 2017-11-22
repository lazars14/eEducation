package com.eEducation.ftn.web.dto;

public class GradeDTO {
	
	private Integer id;
	private Float points;
    private Integer grade;
    private CourseDTO courseId;
    private StudentDTO studentId;
	
	public GradeDTO() {}

	public GradeDTO(Integer id, Float points, Integer grade, CourseDTO courseId, StudentDTO studentId) {
		this.id = id;
		this.points = points;
		this.grade = grade;
		this.courseId = courseId;
		this.studentId = studentId;
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

	public CourseDTO getCourseId() {
		return courseId;
	}

	public void setCourseId(CourseDTO courseId) {
		this.courseId = courseId;
	}

	public StudentDTO getStudentId() {
		return studentId;
	}

	public void setStudentId(StudentDTO studentId) {
		this.studentId = studentId;
	}

	public Float getPoints() {
		return points;
	}

	public void setPoints(Float points) {
		this.points = points;
	}
}
