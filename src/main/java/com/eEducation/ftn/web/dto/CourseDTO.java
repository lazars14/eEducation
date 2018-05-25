package com.eEducation.ftn.web.dto;

import com.eEducation.ftn.model.Course;

public class CourseDTO {
	
	private Integer id;
    private String name;
    private String description;
    private Integer espbPoints;
    private TeacherDTO teacher;
	
	public CourseDTO() {}
	
	public CourseDTO(Course course) {
		this.id = course.getId();
		this.name = course.getName();
		this.description = course.getDescription();
		this.espbPoints = course.getEspbPoints();
		this.teacher = new TeacherDTO(course.getTeacher());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
	
	public Integer getEspbPoints() {
		return espbPoints;
	}

	public void setEspbPoints(Integer espbPoints) {
		this.espbPoints = espbPoints;
	}
	
	public TeacherDTO getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherDTO teacher) {
		this.teacher = teacher;
	}
}
