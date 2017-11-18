package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Rank;

public class RankDTO {
	
	private Integer id;
    private String name;
	
	public RankDTO() {}
	
	public RankDTO(Rank rank){
		this.id = rank.getId();
		this.name = rank.getName();
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
}
