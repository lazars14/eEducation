package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Colloquium;
import com.eEducation.ftn.model.Student;

public class ColloquiumResultDTO {
	
	private Integer id;
    private Float points;
    private Colloquium colloquiumId;
    private Student studentId;
    
    public ColloquiumResultDTO() {}

	public ColloquiumResultDTO(Integer id, Float points, Colloquium colloquiumId, Student studentId) {
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

	public Colloquium getColloquiumId() {
		return colloquiumId;
	}

	public void setColloquiumId(Colloquium colloquiumId) {
		this.colloquiumId = colloquiumId;
	}

	public Student getStudentId() {
		return studentId;
	}

	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}
    
}
