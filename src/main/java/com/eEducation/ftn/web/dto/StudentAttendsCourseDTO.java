package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.StudentAttendsCourse;

public class StudentAttendsCourseDTO {
	
	private Integer id;
    private Boolean deleted;
    private Student studentId;
    private Course courseId;
	
	public StudentAttendsCourseDTO() {}

	public StudentAttendsCourseDTO(StudentAttendsCourse sac) {
		this.id = sac.getId();
		this.deleted = sac.getDeleted();
		this.studentId = sac.getStudentId();
		this.courseId = sac.getCourseId();
	}
	
	public StudentAttendsCourseDTO(Integer id, Boolean deleted, Student studentId, Course courseId) {
		super();
		this.id = id;
		this.deleted = deleted;
		this.studentId = studentId;
		this.courseId = courseId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}
}
