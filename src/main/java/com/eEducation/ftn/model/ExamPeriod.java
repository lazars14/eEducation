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
 * @author lazar
 */
@Entity
@Table(name = "exam_period")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamPeriod.findAll", query = "SELECT e FROM ExamPeriod e"),
    @NamedQuery(name = "ExamPeriod.findById", query = "SELECT e FROM ExamPeriod e WHERE e.id = :id"),
    @NamedQuery(name = "ExamPeriod.findByName", query = "SELECT e FROM ExamPeriod e WHERE e.name = :name"),
    @NamedQuery(name = "ExamPeriod.findByStartDate", query = "SELECT e FROM ExamPeriod e WHERE e.startDate = :startDate"),
    @NamedQuery(name = "ExamPeriod.findByEndDate", query = "SELECT e FROM ExamPeriod e WHERE e.endDate = :endDate")})
public class ExamPeriod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @OneToMany(mappedBy = "examPeriodId")
    private Collection<StudentExamEntry> studentExamEntryCollection;

    public ExamPeriod() {
    }

    public ExamPeriod(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @XmlTransient
    public Collection<StudentExamEntry> getStudentExamEntryCollection() {
        return studentExamEntryCollection;
    }

    public void setStudentExamEntryCollection(Collection<StudentExamEntry> studentExamEntryCollection) {
        this.studentExamEntryCollection = studentExamEntryCollection;
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
        if (!(object instanceof ExamPeriod)) {
            return false;
        }
        ExamPeriod other = (ExamPeriod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.ExamPeriod[ id=" + id + " ]";
    }
    
}
