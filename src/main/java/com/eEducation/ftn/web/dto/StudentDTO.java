package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Class;
import com.eEducation.ftn.model.Student;

public class StudentDTO {
	
	private Integer id;
    private String indexNumber;
    private String firstname;
    private String lastname;
    private String accountNumber;
    private Integer studYear;
    private String email;
    private String sPassword;
    private Integer espbPoints;
    private Boolean deleted;
    private Class classId;
	
	public StudentDTO() {}

	public StudentDTO(Student student) {
		this.id = student.getId();
		this.indexNumber = student.getIndexNumber();
		this.firstname = student.getFirstname();
		this.lastname = student.getLastname();
		this.accountNumber = student.getAccountNumber();
		this.studYear = student.getStudYear();
		this.email = student.getEmail();
		this.sPassword = student.getSPassword();
		this.espbPoints = student.getEspbPoints();
		this.deleted = student.getDeleted();
		this.classId = student.getClassId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getStudYear() {
		return studYear;
	}

	public void setStudYear(Integer studYear) {
		this.studYear = studYear;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getsPassword() {
		return sPassword;
	}

	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}

	public Integer getEspbPoints() {
		return espbPoints;
	}

	public void setEspbPoints(Integer espbPoints) {
		this.espbPoints = espbPoints;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Class getClassId() {
		return classId;
	}

	public void setClassId(Class classId) {
		this.classId = classId;
	}
}
