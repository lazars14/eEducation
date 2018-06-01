package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.ExamTerm;

public class ExamTermDTO {
	
	private Integer id;
    private CourseDTO course;
    private ExamPeriodDTO examPeriod;
    private Date examDate;
    private String classRoom;

    
	public ExamTermDTO() {}

	public ExamTermDTO(ExamTerm examTerm) {
		this.id = examTerm.getId();
		this.course = new CourseDTO(examTerm.getCourse());
		this.examPeriod = new ExamPeriodDTO(examTerm.getExamPeriod());
		this.examDate = examTerm.getExamDate();
		this.classRoom = examTerm.getClassRoom();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}

	public ExamPeriodDTO getExamPeriod() {
		return examPeriod;
	}

	public void setExamPeriod(ExamPeriodDTO examPeriod) {
		this.examPeriod = examPeriod;
	}
	
	public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}
