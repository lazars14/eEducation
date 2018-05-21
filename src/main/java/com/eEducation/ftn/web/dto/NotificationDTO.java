package com.eEducation.ftn.web.dto;

import java.util.Date;

public class NotificationDTO {
	
	private Integer id;
    private String message;
    private Date nDate;
    private CourseDTO course;
    private CourseFileDTO document;
	
	public NotificationDTO() {}

	public NotificationDTO(Notification notification) {
		this.id = notification.getId();
		this.message = notification.getMessage();
		this.nDate = notification.getNDate();
		this.course = new CourseDTO(notification.getCourse());
		this.document = new CourseFile(notification.getDocument());
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

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}

	public CourseFileDTO getDocument() {
		return document;
	}

	public void setDocument(CourseFileDTO document) {
		this.document = document;
	}
	
}
