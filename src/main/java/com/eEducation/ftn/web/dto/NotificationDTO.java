package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.Class;
import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.Document;
import com.eEducation.ftn.model.Notification;

public class NotificationDTO {
	
	private Integer id;
    private String message;
    private Date nDate;
    private Boolean deleted;
    private Course courseId;
    private Class classId;
    private Document documentId;
	
	public NotificationDTO() {}

	public NotificationDTO(Notification notification) {
		this.id = notification.getId();
		this.message = notification.getMessage();
		this.nDate = notification.getNDate();
		this.deleted = notification.getDeleted();
		this.courseId = notification.getCourseId();
		this.classId = notification.getClassId();
		this.documentId = notification.getDocumentId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getnDate() {
		return nDate;
	}

	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}

	public Class getClassId() {
		return classId;
	}

	public void setClassId(Class classId) {
		this.classId = classId;
	}

	public Document getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Document documentId) {
		this.documentId = documentId;
	}
}
