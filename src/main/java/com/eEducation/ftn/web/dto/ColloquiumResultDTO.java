package com.eEducation.ftn.web.dto;

public class ColloquiumResultDTO {
	
	private Integer id;
    private Float points;
    private ColloquiumDTO colloquiumId;
    private StudentDTO studentId;
    
    public ColloquiumResultDTO() {}

	public ColloquiumResultDTO(Integer id, Float points, ColloquiumDTO colloquiumId, StudentDTO studentId) {
		super();
		this.id = id;
		this.points = points;
		this.colloquiumId = colloquiumId;
		this.studentId = studentId;
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
    
}
