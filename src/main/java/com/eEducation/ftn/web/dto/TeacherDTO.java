package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Class;
import com.eEducation.ftn.model.Rank;
import com.eEducation.ftn.model.Teacher;

public class TeacherDTO {
	
	private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String sPassword;
    private Boolean deleted;
    private Rank rank;
    private Class classId;
	
	public TeacherDTO() {}

	public TeacherDTO(Teacher teacher) {
		this.id = teacher.getId();
		this.firstname = teacher.getFirstname();
		this.lastname = teacher.getLastname();
		this.email = teacher.getEmail();
		this.sPassword = teacher.getSPassword();
		this.deleted = teacher.getDeleted();
		this.rank = teacher.getRank();
		this.classId = teacher.getClassId();
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Class getClassId() {
		return classId;
	}

	public void setClassId(Class classId) {
		this.classId = classId;
	}
}
