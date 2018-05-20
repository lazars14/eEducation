package com.eEducation.ftn.web.dto;

public class StudentAttendsCourseDTO {
	
	private Integer id;
    private StudentDTO studentId;
    private CourseDTO courseId;
	
	public StudentAttendsCourseDTO() {}

	public StudentAttendsCourseDTO(StudentAttendsCourse sac) {
		this.id = sac.getId();
		this.studentId = new StudentDTO(sac.getStudentId());
		this.courseId = new CourseDTO(sac.getCourseId());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudentDTO getStudentId() {
		return studentId;
	}

	public void setStudentId(StudentDTO studentId) {
		this.studentId = studentId;
	}

	public CourseDTO getCourseId() {
		return courseId;
	}

	public void setCourseId(CourseDTO courseId) {
		this.courseId = courseId;
	}
}
