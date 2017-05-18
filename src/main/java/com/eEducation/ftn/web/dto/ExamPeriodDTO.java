package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.ExamPeriod;

public class ExamPeriodDTO {
	
	private Integer id;
    private Date startDate;
    private Date endDate;
    private Boolean deleted;
	
	public ExamPeriodDTO() {}

	public ExamPeriodDTO(ExamPeriod examPeriod) {
		this.id = examPeriod.getId();
		this.startDate = examPeriod.getStartDate();
		this.endDate = examPeriod.getEndDate();
		this.deleted = examPeriod.getDeleted();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
