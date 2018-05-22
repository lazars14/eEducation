package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.Colloquium;

public class ColloquiumDTO {
	
	private Integer id;
	private Float maxPoints;
    private String examType;
    private Date examDateTime;
    private CourseDTO course;
    
    public ColloquiumDTO() {}

	public ColloquiumDTO(Colloquium colloquium) {
		super();
		this.id = colloquium.getId();
		this.maxPoints = colloquium.getMaxPoints();
		this.examType = colloquium.getExamType();
		this.examDateTime = colloquium.getExamDateTime();
		this.course = new CourseDTO(colloquium.getCourse());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(Float maxPoints) {
		this.maxPoints = maxPoints;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public Date getExamDateTime() {
		return examDateTime;
	}

	public void setExamDateTime(Date examDateTime) {
		this.examDateTime = examDateTime;
	}

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}
    
}
