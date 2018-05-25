package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Student;

public class StudentDTO {
	
	private Integer id;
    private String indexNumber;
    private String firstname;
    private String lastname;
    private String accountNumber;
    private String referenceNumber;
    private Integer studYear;
    private Integer studYearOrdNum;
    private String email;
    private String sPassword;
    private Integer espbPoints;
    private CollegeDirectionDTO direction;
	
	public StudentDTO() {}

	public StudentDTO(Student student) {
		this.id = student.getId();
		this.indexNumber = student.getIndexNumber();
		this.firstname = student.getFirstname();
		this.lastname = student.getLastname();
		this.accountNumber = student.getAccountNumber();
		this.referenceNumber = student.getReferenceNumber();
		this.studYear = student.getStudYear();
		this.studYearOrdNum = student.getStudYearOrdNum();
		this.email = student.getEmail();
		this.sPassword = student.getSPassword();
		this.espbPoints = student.getEspbPoints();
		this.direction = new CollegeDirectionDTO(student.getCollegeDirection());
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
	
	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
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

	public CollegeDirectionDTO getCollegeDirection() {
		return direction;
	}

	public void setCollegeDirection(CollegeDirectionDTO direction) {
		this.direction = direction;
	}
	
	public Integer getStudYearOrdNum() {
		return studYearOrdNum;
	}

	public void setStudYearOrdNum(Integer studYearOrdNum) {
		this.studYearOrdNum = studYearOrdNum;
	}
}
