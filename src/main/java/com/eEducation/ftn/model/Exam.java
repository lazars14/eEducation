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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lazar Stijakovic
 */
@Entity
@Table(name = "exam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e")
    , @NamedQuery(name = "Exam.findById", query = "SELECT e FROM Exam e WHERE e.id = :id")
    , @NamedQuery(name = "Exam.findByExamType", query = "SELECT e FROM Exam e WHERE e.examType = :examType")
    , @NamedQuery(name = "Exam.findByExamDateTime", query = "SELECT e FROM Exam e WHERE e.examDateTime = :examDateTime")
    , @NamedQuery(name = "Exam.findByExamPoints", query = "SELECT e FROM Exam e WHERE e.examPoints = :examPoints")
    , @NamedQuery(name = "Exam.findByExamGrade", query = "SELECT e FROM Exam e WHERE e.examGrade = :examGrade")
    , @NamedQuery(name = "Exam.findByDeleted", query = "SELECT e FROM Exam e WHERE e.deleted = :deleted")})
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "examType")
    private String examType;
    @Column(name = "examDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date examDateTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "examPoints")
    private Float examPoints;
    @Column(name = "examGrade")
    private Integer examGrade;
    @Column(name = "deleted")
    private Boolean deleted;
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    @ManyToOne
    private Course courseId;
    @OneToMany(mappedBy = "examId")
    private Collection<ExamResult> examResultCollection;

    public Exam() {
    }

    public Exam(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public Date getExamDateTime() {
        return examDateTime;
    }

    public void setExamDateTime(Date examDateTime) {
        this.examDateTime = examDateTime;
    }

    public Float getExamPoints() {
        return examPoints;
    }

    public void setExamPoints(Float examPoints) {
        this.examPoints = examPoints;
    }

    public Integer getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(Integer examGrade) {
        this.examGrade = examGrade;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    @XmlTransient
    public Collection<ExamResult> getExamResultCollection() {
        return examResultCollection;
    }

    public void setExamResultCollection(Collection<ExamResult> examResultCollection) {
        this.examResultCollection = examResultCollection;
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
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eEducation.ftn.model.Exam[ id=" + id + " ]";
    }
    
}
