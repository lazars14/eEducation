package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.StudentExamEntry;

public class StudentExamEntryDTO {
	
	private Integer id;
    private StudentDTO student;
    private ExamTermDTO examTerm;
    private GradeDTO grade;
    
    public StudentExamEntryDTO() {}

	public StudentExamEntryDTO(StudentExamEntry see) {
		super();
		this.id = see.getId();
		this.student = new StudentDTO(see.getStudent());
		this.examTerm = new ExamTermDTO(see.getExamTerm());
		this.grade = new GradeDTO(see.getGrade());
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

	public ExamTermDTO getExamTerm() {
		return examTerm;
	}

	public void setExamTerm(ExamTermDTO examTerm) {
		this.examTerm = examTerm;
	}
	
	public GradeDTO getGrade() {
		return grade;
	}

	public void setGrade(GradeDTO grade) {
		this.grade = grade;
	}
}
