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
 * @author lazar
 */
@Entity
@Table(name = "student_document")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentDocument.findAll", query = "SELECT s FROM StudentDocument s"),
    @NamedQuery(name = "StudentDocument.findById", query = "SELECT s FROM StudentDocument s WHERE s.id = :id"),
    @NamedQuery(name = "StudentDocument.findByDocumentName", query = "SELECT s FROM StudentDocument s WHERE s.documentName = :documentName"),
    @NamedQuery(name = "StudentDocument.findByDocumentType", query = "SELECT s FROM StudentDocument s WHERE s.documentType = :documentType"),
    @NamedQuery(name = "StudentDocument.findByDocumentURL", query = "SELECT s FROM StudentDocument s WHERE s.documentURL = :documentURL"),
    @NamedQuery(name = "StudentDocument.findByMimeType", query = "SELECT s FROM StudentDocument s WHERE s.mimeType = :mimeType")})
public class StudentDocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "documentName")
    private String documentName;
    @Column(name = "documentType")
    private String documentType;
    @Column(name = "documentURL")
    private String documentURL;
    @Column(name = "mimeType")
    private String mimeType;
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    @ManyToOne
    private Student student;
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    @ManyToOne
    private Course course;

    public StudentDocument() {
    }

    public StudentDocument(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentURL() {
        return documentURL;
    }

    public void setDocumentURL(String documentURL) {
        this.documentURL = documentURL;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
        if (!(object instanceof StudentDocument)) {
            return false;
        }
        StudentDocument other = (StudentDocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.StudentDocument[ id=" + id + " ]";
    }
    
}
