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
@Table(name = "courseFile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseFile.findAll", query = "SELECT c FROM CourseFile c"),
    @NamedQuery(name = "CourseFile.findById", query = "SELECT c FROM CourseFile c WHERE c.id = :id"),
    @NamedQuery(name = "CourseFile.findByDocumentName", query = "SELECT c FROM CourseFile c WHERE c.documentName = :documentName"),
    @NamedQuery(name = "CourseFile.findByDocumentType", query = "SELECT c FROM CourseFile c WHERE c.documentType = :documentType"),
    @NamedQuery(name = "CourseFile.findByDocumentURL", query = "SELECT c FROM CourseFile c WHERE c.documentURL = :documentURL"),
    @NamedQuery(name = "CourseFile.findByMimeType", query = "SELECT c FROM CourseFile c WHERE c.mimeType = :mimeType")})
public class CourseFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "documentName")
    private String documentName;
    @Column(name = "documentType")
    private String documentType;
    @Column(name = "documentURL")
    private String documentURL;
    @Column(name = "mimeType")
    private String mimeType;
    @JoinColumn(name = "courseLessonId", referencedColumnName = "id")
    @ManyToOne
    private CourseLesson courseLesson;
    @OneToMany(mappedBy = "documentId")
    private Collection<Notification> notificationCollection;

    public CourseFile() {
    }

    public CourseFile(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public CourseLesson getCourseLesson() {
        return courseLesson;
    }

    public void setCourseLesson(CourseLesson courseLesson) {
        this.courseLesson = courseLesson;
    }

    @XmlTransient
    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
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
        if (!(object instanceof CourseFile)) {
            return false;
        }
        CourseFile other = (CourseFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.CourseFile[ id=" + id + " ]";
    }
    
}
