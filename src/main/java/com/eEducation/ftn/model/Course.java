/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eEducation.ftn.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lazar
 */
@Entity
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"),
    @NamedQuery(name = "Course.findByCourseName", query = "SELECT c FROM Course c WHERE c.courseName = :courseName"),
    @NamedQuery(name = "Course.findByEspbPoints", query = "SELECT c FROM Course c WHERE c.espbPoints = :espbPoints")})
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "courseName")
    private String courseName;
    @Column(name = "espbPoints")
    private Integer espbPoints;
    @OneToMany(mappedBy = "courseId")
    private Collection<StudentExamEntry> studentExamEntryCollection;
    @OneToMany(mappedBy = "courseId")
    private Collection<CourseLesson> courseLessonCollection;
    @OneToMany(mappedBy = "courseId")
    private Collection<StudentAttendsCourse> studentAttendsCourseCollection;
    @OneToMany(mappedBy = "courseId")
    private Collection<TeacherTeachesCourse> teacherTeachesCourseCollection;
    @OneToMany(mappedBy = "courseId")
    private Collection<Colloquium> colloquiumCollection;
    @OneToMany(mappedBy = "courseId")
    private Collection<Notification> notificationCollection;
    @OneToMany(mappedBy = "courseId")
    private Collection<Grade> gradeCollection;
    @JoinColumn(name = "teacherId", referencedColumnName = "id")
    @ManyToOne
    private Teacher teacher;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getEspbPoints() {
        return espbPoints;
    }

    public void setEspbPoints(Integer espbPoints) {
        this.espbPoints = espbPoints;
    }

    @XmlTransient
    public Collection<StudentExamEntry> getStudentExamEntryCollection() {
        return studentExamEntryCollection;
    }

    public void setStudentExamEntryCollection(Collection<StudentExamEntry> studentExamEntryCollection) {
        this.studentExamEntryCollection = studentExamEntryCollection;
    }

    @XmlTransient
    public Collection<CourseLesson> getCourseLessonCollection() {
        return courseLessonCollection;
    }

    public void setCourseLessonCollection(Collection<CourseLesson> courseLessonCollection) {
        this.courseLessonCollection = courseLessonCollection;
    }

    @XmlTransient
    public Collection<StudentAttendsCourse> getStudentAttendsCourseCollection() {
        return studentAttendsCourseCollection;
    }

    public void setStudentAttendsCourseCollection(Collection<StudentAttendsCourse> studentAttendsCourseCollection) {
        this.studentAttendsCourseCollection = studentAttendsCourseCollection;
    }

    @XmlTransient
    public Collection<TeacherTeachesCourse> getTeacherTeachesCourseCollection() {
        return teacherTeachesCourseCollection;
    }

    public void setTeacherTeachesCourseCollection(Collection<TeacherTeachesCourse> teacherTeachesCourseCollection) {
        this.teacherTeachesCourseCollection = teacherTeachesCourseCollection;
    }

    @XmlTransient
    public Collection<Colloquium> getColloquiumCollection() {
        return colloquiumCollection;
    }

    public void setColloquiumCollection(Collection<Colloquium> colloquiumCollection) {
        this.colloquiumCollection = colloquiumCollection;
    }

    @XmlTransient
    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

    @XmlTransient
    public Collection<Grade> getGradeCollection() {
        return gradeCollection;
    }

    public void setGradeCollection(Collection<Grade> gradeCollection) {
        this.gradeCollection = gradeCollection;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.Course[ id=" + id + " ]";
    }
    
}
