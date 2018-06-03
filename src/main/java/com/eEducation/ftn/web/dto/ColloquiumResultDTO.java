package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.ColloquiumResult;

public class ColloquiumResultDTO {
	
	private Long id;
    private Float points;
    private ColloquiumDTO colloquium;
    private StudentDTO student;
    private StudentDocumentDTO document;
    
    public ColloquiumResultDTO() {}

	public ColloquiumResultDTO(ColloquiumResult cr) {
		super();
		this.id = cr.getId();
		this.points = cr.getPoints();
		this.colloquium = new ColloquiumDTO(cr.getColloquium());
		this.student = new StudentDTO(cr.getStudent());
		this.document = new StudentDocumentDTO(cr.getDocument());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getPoints() {
		return points;
	}

	public void setPoints(Float points) {
		this.points = points;
	}

	public ColloquiumDTO getColloquium() {
		return colloquium;
	}

	public void setColloquium(ColloquiumDTO colloquium) {
		this.colloquium = colloquium;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	
	public StudentDocumentDTO getDocument() {
		return document;
	}

	public void setDocument(StudentDocumentDTO document) {
		this.document = document;
	}
    
}
