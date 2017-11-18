package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.Course;

public class ColloquiumDTO {
	
	private Integer id;
	private Float maxPoints;
    private String examType;
    private Date examDateTime;
    private Course courseId;
    
    public ColloquiumDTO() {}

	public ColloquiumDTO(Integer id, Float maxPoints, String examType, Date examDateTime, Course courseId) {
		super();
		this.id = id;
		this.maxPoints = maxPoints;
		this.examType = examType;
		this.examDateTime = examDateTime;
		this.courseId = courseId;
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

	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}
    
}
