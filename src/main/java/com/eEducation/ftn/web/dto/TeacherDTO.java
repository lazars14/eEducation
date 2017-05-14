package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Teacher;

public class TeacherDTO {
	
	private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String sPassword;
    private String rank;
    private Boolean deleted;
	
	public TeacherDTO() {}
	
	public TeacherDTO(Teacher teacher) {
		this.id = teacher.getId();
		this.firstname = teacher.getFirstname();
		this.lastname = teacher.getLastname();
		this.email = teacher.getEmail();
		this.sPassword = teacher.getSPassword();
		this.rank = teacher.getRank();
		this.deleted = teacher.getDeleted();
	}

	public TeacherDTO(Integer id, String firstname, String lastname, String email, String sPassword, String rank,
			Boolean deleted) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.sPassword = sPassword;
		this.rank = rank;
		this.deleted = deleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
