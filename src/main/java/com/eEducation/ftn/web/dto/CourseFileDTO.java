package com.eEducation.ftn.web.dto;

public class CourseFileDTO {
	
	private Integer id;
    private String documentName;
    private String documentType;
    private String documentURL;
    private String mimeType;
    private CourseLessonDTO courseLessonId;
    
    public CourseFileDTO() {}

	public CourseFileDTO(Integer id, String documentName, String documentType, String documentURL, String mimeType,
			CourseLessonDTO courseLessonId) {
		super();
		this.id = id;
		this.documentName = documentName;
		this.documentType = documentType;
		this.documentURL = documentURL;
		this.mimeType = mimeType;
		this.courseLessonId = courseLessonId;
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

	public CourseLessonDTO getCourseLessonId() {
		return courseLessonId;
	}

	public void setCourseLessonId(CourseLessonDTO courseLessonId) {
		this.courseLessonId = courseLessonId;
	}
    
}
