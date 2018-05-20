package com.eEducation.ftn.web.dto;

public class CourseFileDTO {
	
	private Integer id;
    private String documentName;
    private String documentType;
    private String documentURL;
    private String mimeType;
    private CourseLessonDTO courseLessonId;
    
    public CourseFileDTO() {}

	public CourseFileDTO(CourseFile courseFile) {
		super();
		this.id = courseFile.getId();
		this.documentName = courseFile.getDocumentName();
		this.documentType = courseFile.getDocumentType();
		this.documentURL = courseFile.getDocumentURL();
		this.mimeType = courseFile.getMimeType();
		this.courseLessonId = new CourseLesson(courseFile.getCourseLessonId());
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
