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
 * @author lazar
 */
@Entity
@Table(name = "colloquium")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colloquium.findAll", query = "SELECT c FROM Colloquium c"),
    @NamedQuery(name = "Colloquium.findById", query = "SELECT c FROM Colloquium c WHERE c.id = :id"),
    @NamedQuery(name = "Colloquium.findByMaxPoints", query = "SELECT c FROM Colloquium c WHERE c.maxPoints = :maxPoints"),
    @NamedQuery(name = "Colloquium.findByExamType", query = "SELECT c FROM Colloquium c WHERE c.examType = :examType"),
    @NamedQuery(name = "Colloquium.findByExamDateTime", query = "SELECT c FROM Colloquium c WHERE c.examDateTime = :examDateTime")})
public class Colloquium implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "maxPoints")
    private Float maxPoints;
    @Column(name = "examType")
    private String examType;
    @Column(name = "examDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date examDateTime;
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    @ManyToOne
    private Course courseId;
    @OneToMany(mappedBy = "colloquiumId")
    private Collection<ColloquiumResult> colloquiumResultCollection;

    public Colloquium() {
    }

    public Colloquium(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Float maxPoints) {
        this.maxPoints = maxPoints;
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

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    @XmlTransient
    public Collection<ColloquiumResult> getColloquiumResultCollection() {
        return colloquiumResultCollection;
    }

    public void setColloquiumResultCollection(Collection<ColloquiumResult> colloquiumResultCollection) {
        this.colloquiumResultCollection = colloquiumResultCollection;
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
        if (!(object instanceof Colloquium)) {
            return false;
        }
        Colloquium other = (Colloquium) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.Colloquium[ id=" + id + " ]";
    }
    
}
