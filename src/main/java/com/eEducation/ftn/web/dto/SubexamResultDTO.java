package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.Subexam;
import com.eEducation.ftn.model.SubexamResult;

public class SubexamResultDTO {
	
	private Integer id;
    private Boolean passed;
    private Boolean deleted;
    private Subexam subexamId;
    private Student studentId;
	
	public SubexamResultDTO() {}

	public SubexamResultDTO(SubexamResult ser) {
		this.id = ser.getId();
		this.passed = ser.getPassed();
		this.deleted = ser.getDeleted();
		this.subexamId = ser.getSubexamId();
		this.studentId = ser.getStudentId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Subexam getSubexamId() {
		return subexamId;
	}

	public void setSubexamId(Subexam subexamId) {
		this.subexamId = subexamId;
	}

	public Student getStudentId() {
		return studentId;
	}

	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}
}
