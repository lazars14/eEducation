package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.CourseFile;
import com.eEducation.ftn.model.Notification;

public class NotificationDTO {
	
	private Integer id;
    private String message;
    private Date nDate;
    private Course courseId;
    private CourseFile documentId;
	
	public NotificationDTO() {}

	public NotificationDTO(Notification notification) {
		this.id = notification.getId();
		this.message = notification.getMessage();
		this.nDate = notification.getNDate();
		this.courseId = notification.getCourseId();
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

	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}

	public CourseFile getDocumentId() {
		return documentId;
	}

	public void setDocumentId(CourseFile documentId) {
		this.documentId = documentId;
	}
	
}
