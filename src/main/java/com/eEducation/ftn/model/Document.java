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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lazar Stijakovic
 */
@Entity
@Table(name = "document")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d")
    , @NamedQuery(name = "Document.findById", query = "SELECT d FROM Document d WHERE d.id = :id")
    , @NamedQuery(name = "Document.findByDocumentName", query = "SELECT d FROM Document d WHERE d.documentName = :documentName")
    , @NamedQuery(name = "Document.findByDocumentType", query = "SELECT d FROM Document d WHERE d.documentType = :documentType")
    , @NamedQuery(name = "Document.findByDocumentURL", query = "SELECT d FROM Document d WHERE d.documentURL = :documentURL")
    , @NamedQuery(name = "Document.findByMimeType", query = "SELECT d FROM Document d WHERE d.mimeType = :mimeType")
    , @NamedQuery(name = "Document.findByDeleted", query = "SELECT d FROM Document d WHERE d.deleted = :deleted")})
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "documentName")
    private String documentName;
    @Size(max = 30)
    @Column(name = "documentType")
    private String documentType;
    @Size(max = 200)
    @Column(name = "documentURL")
    private String documentURL;
    @Size(max = 30)
    @Column(name = "mimeType")
    private String mimeType;
    @Column(name = "deleted")
    private Boolean deleted;
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    @ManyToOne
    private Student studentId;
    @OneToMany(mappedBy = "documentId")
    private Collection<Notification> notificationCollection;
    @OneToMany(mappedBy = "documentId")
    private Collection<Subexam> subexamCollection;

    public Document() {
    }

    public Document(Integer id) {
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    @XmlTransient
    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

    @XmlTransient
    public Collection<Subexam> getSubexamCollection() {
        return subexamCollection;
    }

    public void setSubexamCollection(Collection<Subexam> subexamCollection) {
        this.subexamCollection = subexamCollection;
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
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eEducation.ftn.model.Document[ id=" + id + " ]";
    }
    
}
