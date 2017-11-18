package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.ExamPeriod;
import com.eEducation.ftn.model.Student;

public class StudentExamEntryDTO {
	
	private Integer id;
    private Date eDate;
    private Student studentId;
    private Course courseId;
    private ExamPeriod examPeriodId;
    
    public StudentExamEntryDTO() {}

	public StudentExamEntryDTO(Integer id, Date eDate, Student studentId, Course courseId, ExamPeriod examPeriodId) {
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

	public ExamPeriod getExamPeriodId() {
		return examPeriodId;
	}

	public void setExamPeriodId(ExamPeriod examPeriodId) {
		this.examPeriodId = examPeriodId;
	}
    
}
