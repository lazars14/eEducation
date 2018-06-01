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
@Table(name = "course_lesson")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseLesson.findAll", query = "SELECT c FROM CourseLesson c"),
    @NamedQuery(name = "CourseLesson.findById", query = "SELECT c FROM CourseLesson c WHERE c.id = :id"),
    @NamedQuery(name = "CourseLesson.findByName", query = "SELECT c FROM CourseLesson c WHERE c.name = :name"),
    @NamedQuery(name = "CourseLesson.findByDescription", query = "SELECT c FROM CourseLesson c WHERE c.description = :description")})
public class CourseLesson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "courseLessonId")
    private Collection<CourseFile> courseFileCollection;
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    @ManyToOne
    private Course course;

    public CourseLesson() {
    }

    public CourseLesson(Integer id) {
        this.id = id;
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

    @XmlTransient
    public Collection<CourseFile> getCourseFileCollection() {
        return courseFileCollection;
    }

    public void setCourseFileCollection(Collection<CourseFile> courseFileCollection) {
        this.courseFileCollection = courseFileCollection;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
        if (!(object instanceof CourseLesson)) {
            return false;
        }
        CourseLesson other = (CourseLesson) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.CourseLesson[ id=" + id + " ]";
    }
    
}
