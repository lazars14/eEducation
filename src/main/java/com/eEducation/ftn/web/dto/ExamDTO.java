package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.Exam;

public class ExamDTO {
	
	private Integer id;
    private Float maxPoints;
    private String examType;
    private Date examDateTime;
    private Boolean deleted;
    private Course courseId;
	
	public ExamDTO() {}

	public ExamDTO(Exam exam) {
		this.id = exam.getId();
		this.maxPoints = exam.getMaxPoints();
		this.examType = exam.getExamType();
		this.examDateTime = exam.getExamDateTime();
		this.deleted = exam.getDeleted();
		this.courseId = exam.getCourseId();
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}
}
