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
    private ClassDTO classId;
	
	public StudentDTO() {}

	public StudentDTO(Integer id, String indexNumber, String firstname,
			String lastname, String accountNumber, Integer studYear, 
			Integer studYearOrdNum, String email, String sPassword,
			Integer espbPoints, ClassDTO classId) {
		this.id = id;
		this.indexNumber = indexNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.accountNumber = accountNumber;
		this.studYear = studYear;
		this.studYearOrdNum = studYearOrdNum;
		this.email = email;
		this.sPassword = sPassword;
		this.espbPoints = espbPoints;
		this.classId = classId;
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

	public ClassDTO getClassId() {
		return classId;
	}

	public void setClassId(ClassDTO classId) {
		this.classId = classId;
	}
	
	public Integer getStudYearOrdNum() {
		return studYearOrdNum;
	}

	public void setStudYearOrdNum(Integer studYearOrdNum) {
		this.studYearOrdNum = studYearOrdNum;
	}
}
