package com.eEducation.ftn.web.dto;


import java.util.Date;

public class StudentExamEntryDTO {
	
	private Integer id;
    private Date eDate;
    private StudentDTO studentId;
    private CourseDTO courseId;
    private ExamPeriodDTO examPeriodId;
    
    public StudentExamEntryDTO() {}

	public StudentExamEntryDTO(Integer id, Date eDate, StudentDTO studentId, CourseDTO courseId,
			ExamPeriodDTO examPeriodId) {
		super();
		this.id = id;
		this.eDate = eDate;
		this.studentId = studentId;
		this.courseId = courseId;
		this.examPeriodId = examPeriodId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
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

	public ExamPeriodDTO getExamPeriodId() {
		return examPeriodId;
	}

	public void setExamPeriodId(ExamPeriodDTO examPeriodId) {
		this.examPeriodId = examPeriodId;
	}

}
