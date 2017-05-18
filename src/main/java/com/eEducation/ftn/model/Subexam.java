/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eEducation.ftn.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lazar Stijakovic
 */
@Entity
@Table(name = "subexam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subexam.findAll", query = "SELECT s FROM Subexam s")
    , @NamedQuery(name = "Subexam.findById", query = "SELECT s FROM Subexam s WHERE s.id = :id")
    , @NamedQuery(name = "Subexam.findByExamPercentage", query = "SELECT s FROM Subexam s WHERE s.examPercentage = :examPercentage")
    , @NamedQuery(name = "Subexam.findByMaxPoints", query = "SELECT s FROM Subexam s WHERE s.maxPoints = :maxPoints")
    , @NamedQuery(name = "Subexam.findByEDate", query = "SELECT s FROM Subexam s WHERE s.eDate = :eDate")
    , @NamedQuery(name = "Subexam.findByDue", query = "SELECT s FROM Subexam s WHERE s.due = :due")
    , @NamedQuery(name = "Subexam.findByDeleted", query = "SELECT s FROM Subexam s WHERE s.deleted = :deleted")})
public class Subexam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "examPercentage")
    private Float examPercentage;
    @Column(name = "maxPoints")
    private Float maxPoints;
    @Column(name = "eDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eDate;
    @Column(name = "due")
    @Temporal(TemporalType.TIMESTAMP)
    private Date due;
    @Column(name = "deleted")
    private Boolean deleted;
    @OneToMany(mappedBy = "subexamId")
    private Collection<SubexamResult> subexamResultCollection;
    @JoinColumn(name = "examId", referencedColumnName = "id")
    @ManyToOne
    private Exam examId;
    @JoinColumn(name = "documentId", referencedColumnName = "id")
    @ManyToOne
    private Document documentId;

    public Subexam() {
    }

    public Subexam(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getExamPercentage() {
        return examPercentage;
    }

    public void setExamPercentage(Float examPercentage) {
        this.examPercentage = examPercentage;
    }

    public Float getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Float maxPoints) {
        this.maxPoints = maxPoints;
    }

    public Date getEDate() {
        return eDate;
    }

    public void setEDate(Date eDate) {
        this.eDate = eDate;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @XmlTransient
    public Collection<SubexamResult> getSubexamResultCollection() {
        return subexamResultCollection;
    }

    public void setSubexamResultCollection(Collection<SubexamResult> subexamResultCollection) {
        this.subexamResultCollection = subexamResultCollection;
    }

    public Exam getExamId() {
        return examId;
    }

    public void setExamId(Exam examId) {
        this.examId = examId;
    }

    public Document getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Document documentId) {
        this.documentId = documentId;
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
        if (!(object instanceof Subexam)) {
            return false;
        }
        Subexam other = (Subexam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eEducation.ftn.model.Subexam[ id=" + id + " ]";
    }
    
}
