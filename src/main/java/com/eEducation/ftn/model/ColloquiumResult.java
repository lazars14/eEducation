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
@Table(name = "colloquiumResult")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ColloquiumResult.findAll", query = "SELECT c FROM ColloquiumResult c"),
    @NamedQuery(name = "ColloquiumResult.findById", query = "SELECT c FROM ColloquiumResult c WHERE c.id = :id"),
    @NamedQuery(name = "ColloquiumResult.findByPoints", query = "SELECT c FROM ColloquiumResult c WHERE c.points = :points")})
public class ColloquiumResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "points")
    private Float points;
    @JoinColumn(name = "colloquiumId", referencedColumnName = "id")
    @ManyToOne
    private Colloquium colloquiumId;
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    @ManyToOne
    private Student studentId;

    public ColloquiumResult() {
    }

    public ColloquiumResult(Integer id) {
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

    public Colloquium getColloquiumId() {
        return colloquiumId;
    }

    public void setColloquiumId(Colloquium colloquiumId) {
        this.colloquiumId = colloquiumId;
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
        if (!(object instanceof ColloquiumResult)) {
            return false;
        }
        ColloquiumResult other = (ColloquiumResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.ColloquiumResult[ id=" + id + " ]";
    }
    
}
