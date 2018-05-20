package com.eEducation.ftn.web.dto;

import java.util.Date;

public class ColloquiumDTO {
	
	private Integer id;
	private Float maxPoints;
    private String examType;
    private Date examDateTime;
    private CourseDTO courseId;
    
    public ColloquiumDTO() {}

	public ColloquiumDTO(Colloquium colloquium) {
		super();
		this.id = colloquium.getId();
		this.maxPoints = colloquium.getMaxPoints();
		this.examType = colloquium.getExamType();
		this.examDateTime = colloquium.getExamDateTime();
		this.courseId = new CourseDTO(colloquium.getCourseId());
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

	public CourseDTO getCourseId() {
		return courseId;
	}

	public void setCourseId(CourseDTO courseId) {
		this.courseId = courseId;
	}
    
}
