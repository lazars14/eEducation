package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Teacher;

public class TeacherDTO {
	
	private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String sPassword;
    private RankDTO rank;
	
	public TeacherDTO() {}

	public TeacherDTO(Teacher teacher) {
		this.id = teacher.getId();
		this.firstname = teacher.getFirstname();
		this.lastname = teacher.getLastname();
		this.email = teacher.getEmail();
		this.sPassword = teacher.getSPassword();
		this.rank = new RankDTO(teacher.getRank());
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

	public RankDTO getRank() {
		return rank;
	}

	public void setRank(RankDTO rank) {
		this.rank = rank;
	}

}
