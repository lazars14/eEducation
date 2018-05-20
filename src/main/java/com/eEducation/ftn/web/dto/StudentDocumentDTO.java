package com.eEducation.ftn.web.dto;

public class StudentDocumentDTO {

	private Integer id;
    private String documentName;
    private String documentType;
    private String documentURL;
    private String mimeType;
    private StudentDTO studentId;

    public StudentDocumentDTO() {}

	public StudentDocumentDTO(StudentDocument studDoc) {
		super();
		this.id = studDoc.getId();
		this.documentName = studDoc.getDocumentName();
		this.documentType = studDoc.getDocumentType();
		this.documentURL = studDoc.getDocumentURL();
		this.mimeType = studDoc.getMimeType();
		this.studentId = new StudentDTO(studDoc.getStudentId());
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

	public StudentDTO getStudentId() {
		return studentId;
	}

	public void setStudentId(StudentDTO studentId) {
		this.studentId = studentId;
	}
    
}
