package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.Document;

public class DocumentDTO {
	
	private Integer id;
    private String documentName;
    private String documentType;
    private String documentURL;
    private String mimeType;
    private Boolean deleted;
    private Student studentId;
	
	public DocumentDTO() {}

	public DocumentDTO(Document document) {
		this.id = document.getId();
		this.documentName = document.getDocumentName();
		this.documentType = document.getDocumentType();
		this.documentURL = document.getDocumentURL();
		this.mimeType = document.getMimeType();
		this.deleted = document.getDeleted();
		this.studentId = document.getStudentId();
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Student getStudentId() {
		return studentId;
	}

	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}
}
