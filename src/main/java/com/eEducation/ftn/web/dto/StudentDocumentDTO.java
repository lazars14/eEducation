package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.StudentDocument;

public class StudentDocumentDTO {

	private Integer id;
    private String documentName;
    private String documentType;
    private String documentURL;
    private String mimeType;
    private StudentDTO student;
    private CourseDTO course;

    public StudentDocumentDTO() {}

	public StudentDocumentDTO(StudentDocument studDoc) {
		super();
		this.id = studDoc.getId();
		this.documentName = studDoc.getDocumentName();
		this.documentType = studDoc.getDocumentType();
		this.documentURL = studDoc.getDocumentURL();
		this.mimeType = studDoc.getMimeType();
		this.student = new StudentDTO(studDoc.getStudent());
		this.course = new CourseDTO(studDoc.getCourse());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentURL() {
		return documentURL;
	}

	public void setDocumentURL(String documentURL) {
		this.documentURL = documentURL;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	
	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}
    
}
