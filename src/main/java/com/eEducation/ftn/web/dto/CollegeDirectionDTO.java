package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.CollegeDirection;

public class CollegeDirectionDTO {
	
	private Long id;
    private String name;
    private Integer numOfYears;
	
	public CollegeDirectionDTO() {}

	public CollegeDirectionDTO(CollegeDirection c) {
		this.id = c.getId();
		this.name = c.getName();
		this.numOfYears = c.getNumOfYears();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
