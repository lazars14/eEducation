package com.eEducation.ftn.web.dto;

public class ColloquiumResultDTO {
	
	private Integer id;
    private Float points;
    private ColloquiumDTO colloquiumId;
    private StudentDTO studentId;
    private StudentDocumentDTO documentId;
    
    public ColloquiumResultDTO() {}

	public ColloquiumResultDTO(ColloquiumResult cr) {
		super();
		this.id = cr.getId();
		this.points = cr.getPoints();
		this.colloquiumId = new ColloquiumDTO(cr.getColloquiumId());
		this.studentId = new StudentDTO(cr.getStudentId());
		this.documentId = new StudentDocument(cr.getDocumentId());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPoints() {
		return points;
	}

	public void setPoints(Float points) {
		this.points = points;
	}

	public ColloquiumDTO getColloquiumId() {
		return colloquiumId;
	}

	public void setColloquiumId(ColloquiumDTO colloquiumId) {
		this.colloquiumId = colloquiumId;
	}

	public StudentDTO getStudentId() {
		return studentId;
	}

	public void setStudentId(StudentDTO studentId) {
		this.studentId = studentId;
	}
	
	public StudentDocumentDTO getDocumentId() {
		return documentId;
	}

	public void setDocumentId(StudentDocumentDTO documentId) {
		this.documentId = documentId;
	}
    
}
