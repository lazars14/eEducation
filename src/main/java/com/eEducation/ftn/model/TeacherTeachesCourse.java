/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eEducation.ftn.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lazar Stijakovic
 */
@Entity
@Table(name = "teacherTeachesCourse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TeacherTeachesCourse.findAll", query = "SELECT t FROM TeacherTeachesCourse t")
    , @NamedQuery(name = "TeacherTeachesCourse.findById", query = "SELECT t FROM TeacherTeachesCourse t WHERE t.id = :id")
    , @NamedQuery(name = "TeacherTeachesCourse.findByDeleted", query = "SELECT t FROM TeacherTeachesCourse t WHERE t.deleted = :deleted")})
public class TeacherTeachesCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "deleted")
    private Boolean deleted;
    @JoinColumn(name = "teacherId", referencedColumnName = "id")
    @ManyToOne
    private Teacher teacherId;
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    @ManyToOne
    private Course courseId;

    public TeacherTeachesCourse() {
    }

    public TeacherTeachesCourse(Integer id) {
        this.id = id;
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

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
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
        if (!(object instanceof TeacherTeachesCourse)) {
            return false;
        }
        TeacherTeachesCourse other = (TeacherTeachesCourse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eEducation.ftn.model.TeacherTeachesCourse[ id=" + id + " ]";
    }
    
}
