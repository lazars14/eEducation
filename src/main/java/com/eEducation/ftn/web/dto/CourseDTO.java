package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Course;

public class CourseDTO {
	
	private Integer id;
    private String courseName;
    private Integer espbPoints;
	
	public CourseDTO() {}
	
	public CourseDTO(Course course) {
		this.id = course.getId();
		this.courseName = course.getCourseName();
		this.espbPoints = course.getEspbPoints();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getEspbPoints() {
		return espbPoints;
	}

	public void setEspbPoints(Integer espbPoints) {
		this.espbPoints = espbPoints;
	}

}
