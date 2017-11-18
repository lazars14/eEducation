package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.StudentAttendsCourse;

public class StudentAttendsCourseDTO {
	
	private Integer id;
    private Student studentId;
    private Course courseId;
	
	public StudentAttendsCourseDTO() {}

	public StudentAttendsCourseDTO(StudentAttendsCourse sat) {
		this.id = sat.getId();
		this.studentId = sat.getStudentId();
		this.courseId = sat.getCourseId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
