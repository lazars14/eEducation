/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eEducation.ftn.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lazar Stijakovic
 */
@Entity
@Table(name = "examPeriod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamPeriod.findAll", query = "SELECT e FROM ExamPeriod e")
    , @NamedQuery(name = "ExamPeriod.findById", query = "SELECT e FROM ExamPeriod e WHERE e.id = :id")
    , @NamedQuery(name = "ExamPeriod.findByStartDate", query = "SELECT e FROM ExamPeriod e WHERE e.startDate = :startDate")
    , @NamedQuery(name = "ExamPeriod.findByEndDate", query = "SELECT e FROM ExamPeriod e WHERE e.endDate = :endDate")
    , @NamedQuery(name = "ExamPeriod.findByDeleted", query = "SELECT e FROM ExamPeriod e WHERE e.deleted = :deleted")})
public class ExamPeriod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "deleted")
    private Boolean deleted;

    public ExamPeriod() {
    }

    public ExamPeriod(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
        return "com.eEducation.ftn.model.ExamPeriod[ id=" + id + " ]";
    }
    
}
