package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.Notification;

public class NotificationDTO {
	
	private Long id;
    private String message;
    private Date nDate;
    private CourseDTO course;
    private CourseFileDTO document;
    private Boolean seen;
    private StudentDTO student;
	
	public NotificationDTO() {}

	public NotificationDTO(Notification notification) {
		this.id = notification.getId();
		this.message = notification.getMessage();
		this.nDate = notification.getNDate();
		this.course = new CourseDTO(notification.getCourse());
		this.document = new CourseFileDTO(notification.getDocument());
		this.seen = notification.getSeen();
		this.student = new StudentDTO(notification.getStudent());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }
    
    public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	
}
