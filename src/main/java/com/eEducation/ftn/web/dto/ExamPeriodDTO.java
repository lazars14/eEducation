package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.ExamPeriod;

public class ExamPeriodDTO {
	
	private Long id;
	private String name;
    private Date startDate;
    private Date endDate;
	
	public ExamPeriodDTO() {}

	public ExamPeriodDTO(ExamPeriod examPeriod) {
		this.id = examPeriod.getId();
		this.name = examPeriod.getName();
		this.startDate = examPeriod.getStartDate();
		this.endDate = examPeriod.getEndDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
