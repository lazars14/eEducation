package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.AppUser;

public class AppUserDTO {
	
	private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String aPassword;
    private String role;
    private Boolean deleted;
	
	public AppUserDTO() {}
	
	public AppUserDTO(AppUser appUser) {
		this.id = appUser.getId();
		this.firstname = appUser.getFirstname();
		this.lastname = appUser.getLastname();
		this.username = appUser.getUsername();
		this.aPassword = appUser.getAPassword();
		this.role = appUser.getRole();
		this.deleted = appUser.getDeleted();
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getaPassword() {
		return aPassword;
	}

	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
