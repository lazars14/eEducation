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
 * @author Lazar
 */
@Entity
@Table(name = "examResult")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamResult.findAll", query = "SELECT e FROM ExamResult e")
    , @NamedQuery(name = "ExamResult.findById", query = "SELECT e FROM ExamResult e WHERE e.id = :id")
    , @NamedQuery(name = "ExamResult.findByPoints", query = "SELECT e FROM ExamResult e WHERE e.points = :points")
    , @NamedQuery(name = "ExamResult.findByDeleted", query = "SELECT e FROM ExamResult e WHERE e.deleted = :deleted")})
public class ExamResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "points")
    private Float points;
    @Column(name = "deleted")
    private Boolean deleted;
    @JoinColumn(name = "examId", referencedColumnName = "id")
    @ManyToOne
    private Exam examId;
    @JoinColumn(name = "studentId", referencedColumnName = "indexNumber")
    @ManyToOne
    private Student studentId;

    public ExamResult() {
    }

    public ExamResult(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPoints() {
        return points;
    }

    public void setPoints(Float points) {
        this.points = points;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Exam getExamId() {
        return examId;
    }

    public void setExamId(Exam examId) {
        this.examId = examId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
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
        if (!(object instanceof ExamResult)) {
            return false;
        }
        ExamResult other = (ExamResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "asdf.ExamResult[ id=" + id + " ]";
    }
    
}
