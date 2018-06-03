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
@Table(name = "teacher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t"),
    @NamedQuery(name = "Teacher.findById", query = "SELECT t FROM Teacher t WHERE t.id = :id"),
    @NamedQuery(name = "Teacher.findByFirstname", query = "SELECT t FROM Teacher t WHERE t.firstname = :firstname"),
    @NamedQuery(name = "Teacher.findByLastname", query = "SELECT t FROM Teacher t WHERE t.lastname = :lastname"),
    @NamedQuery(name = "Teacher.findByEmail", query = "SELECT t FROM Teacher t WHERE t.email = :email"),
    @NamedQuery(name = "Teacher.findBySPassword", query = "SELECT t FROM Teacher t WHERE t.sPassword = :sPassword")})
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "sPassword")
    private String sPassword;
    @OneToMany(mappedBy = "teacherId")
    private Collection<TeacherTeachesCourse> teacherTeachesCourseCollection;
    @JoinColumn(name = "rank", referencedColumnName = "id")
    @ManyToOne
    private Rank rank;
    @OneToMany(mappedBy = "teacherId")
    private Collection<Course> courseCollection;

    public Teacher() {
    }

    public Teacher(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSPassword() {
        return sPassword;
    }

    public void setSPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    @XmlTransient
    public Collection<TeacherTeachesCourse> getTeacherTeachesCourseCollection() {
        return teacherTeachesCourseCollection;
    }

    public void setTeacherTeachesCourseCollection(Collection<TeacherTeachesCourse> teacherTeachesCourseCollection) {
        this.teacherTeachesCourseCollection = teacherTeachesCourseCollection;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @XmlTransient
    public Collection<Course> getCourseCollection() {
        return courseCollection;
    }

    public void setCourseCollection(Collection<Course> courseCollection) {
        this.courseCollection = courseCollection;
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
        if (!(object instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.Teacher[ id=" + id + " ]";
    }
    
}
