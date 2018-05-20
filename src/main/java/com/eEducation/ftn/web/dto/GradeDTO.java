package com.eEducation.ftn.web.dto;

public class GradeDTO {
	
	private Integer id;
	private Float points;
    private Integer grade;
    private CourseDTO courseId;
    private StudentDTO studentId;
	
	public GradeDTO() {}

	public GradeDTO(Grade grade) {
		this.id = grade.getId();
		this.points = grade.getPoints();
		this.grade = grade.getGrade();
		this.courseId = new CourseDTO(grade.getCourseId());
		this.studentId = new StudentDTO(grade.getStudentId());
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
