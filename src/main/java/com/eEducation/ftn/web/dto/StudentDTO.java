package com.eEducation.ftn.web.dto;

public class StudentDTO {
	
	private Integer id;
    private String indexNumber;
    private String firstname;
    private String lastname;
    private String accountNumber;
    private Integer studYear;
    private Integer studYearOrdNum;
    private String email;
    private String sPassword;
    private Integer espbPoints;
    private ClassDTO class;
	
	public StudentDTO() {}

	public StudentDTO(Student student) {
		this.id = student.getId();
		this.indexNumber = student.getIndexNumber();
		this.firstname = student.getFirstname();
		this.lastname = student.getLastname();
		this.accountNumber = student.getAccountNumber();
		this.studYear = student.getStudYear();
		this.studYearOrdNum = student.getStudYearOrdNum();
		this.email = student.getEmail();
		this.sPassword = student.getSPassword();
		this.espbPoints = student.getEspbPoints();
		this.classs = new ClassDTO(student.getClass());
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

	public ClassDTO getClass() {
		return classs;
	}

	public void setClass(ClassDTO classs) {
		this.classs = classs;
	}
	
	public Integer getStudYearOrdNum() {
		return studYearOrdNum;
	}

	public void setStudYearOrdNum(Integer studYearOrdNum) {
		this.studYearOrdNum = studYearOrdNum;
	}
}
