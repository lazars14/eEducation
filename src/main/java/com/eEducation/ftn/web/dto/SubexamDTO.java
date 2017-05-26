package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.Document;
import com.eEducation.ftn.model.Exam;
import com.eEducation.ftn.model.Subexam;

public class SubexamDTO {
	
	private Integer id;
    private Float examPercentage;
    private Float maxPoints;
    private Date eDate;
    private Date due;
    private Boolean deleted;
    private Exam examId;
    private Document documentId;
	
	public SubexamDTO() {}

	public SubexamDTO(Subexam subexam) {
		this.id = subexam.getId();
		this.examPercentage = subexam.getExamPercentage();
		this.maxPoints = subexam.getMaxPoints();
		this.eDate = subexam.getEDate();
		this.due = subexam.getDue();
		this.deleted = subexam.getDeleted();
		this.examId = subexam.getExamId();
		this.documentId = subexam.getDocumentId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getExamPercentage() {
		return examPercentage;
	}

	public void setExamPercentage(Float examPercentage) {
		this.examPercentage = examPercentage;
	}

	public Float getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(Float maxPoints) {
		this.maxPoints = maxPoints;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Exam getExamId() {
		return examId;
	}

	public void setExamId(Exam examId) {
		this.examId = examId;
	}

	public Document getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Document documentId) {
		this.documentId = documentId;
	}
}
