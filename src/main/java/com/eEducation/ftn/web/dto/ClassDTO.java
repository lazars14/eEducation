package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Class;

public class ClassDTO {
	
	private Integer id;
    private String name;
    private Boolean deleted;
	
	public ClassDTO() {}

	public ClassDTO(Class c) {
		this.id = c.getId();
		this.name = c.getName();
		this.deleted = c.getDeleted();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
