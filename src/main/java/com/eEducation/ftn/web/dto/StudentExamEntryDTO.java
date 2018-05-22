package com.eEducation.ftn.web.dto;


import java.util.Date;

import com.eEducation.ftn.model.StudentExamEntry;

public class StudentExamEntryDTO {
	
	private Integer id;
    private Date eDate;
    private StudentDTO student;
    private CourseDTO course;
    private ExamPeriodDTO examPeriod;
    
    public StudentExamEntryDTO() {}

	public StudentExamEntryDTO(StudentExamEntry see) {
		super();
		this.id = see.getId();
		this.eDate = see.getEDate();
		this.student = new StudentDTO(see.getStudent());
		this.course = new CourseDTO(see.getCourse());
		this.examPeriod = new ExamPeriodDTO(see.getExamPeriod());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
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

}
