package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Class;

public class ClassDTO {
	
	private Integer id;
    private String name;
    private Integer numOfYears;
	
	public ClassDTO() {}

	public ClassDTO(Class c) {
		this.id = c.getId();
		this.name = c.getName();
		this.numOfYears = c.getNumOfYears();
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

	public Integer getNumOfYears() {
		return numOfYears;
	}

	public void setNumOfYears(Integer numOfYears) {
		this.numOfYears = numOfYears;
	}

}
