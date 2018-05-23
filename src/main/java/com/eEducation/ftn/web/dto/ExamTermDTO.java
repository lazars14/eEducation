package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.ExamTerm;

public class ExamTermDTO {
	
	private Integer id;
    private StudentDTO student;
    private ExamPeriodDTO examPeriod;
	
	public ExamTermDTO() {}

	public ExamTermDTO(ExamTerm examTerm) {
		this.id = examTerm.getId();
		this.student = new StudentDTO(examTerm.getStudent());
		this.examPeriod = new ExamPeriodDTO(examTerm.getExamPeriod());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public ExamPeriodDTO getExamPeriod() {
		return examPeriod;
	}

	public void setExamPeriod(ExamPeriodDTO examPeriod) {
		this.examPeriod = examPeriod;
	}
}
