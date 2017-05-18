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
 * @author Lazar Stijakovic
 */
@Entity
@Table(name = "subexamResult")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubexamResult.findAll", query = "SELECT s FROM SubexamResult s")
    , @NamedQuery(name = "SubexamResult.findById", query = "SELECT s FROM SubexamResult s WHERE s.id = :id")
    , @NamedQuery(name = "SubexamResult.findByPoints", query = "SELECT s FROM SubexamResult s WHERE s.points = :points")
    , @NamedQuery(name = "SubexamResult.findByPassed", query = "SELECT s FROM SubexamResult s WHERE s.passed = :passed")
    , @NamedQuery(name = "SubexamResult.findByDeleted", query = "SELECT s FROM SubexamResult s WHERE s.deleted = :deleted")})
public class SubexamResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "points")
    private Float points;
    @Column(name = "passed")
    private Boolean passed;
    @Column(name = "deleted")
    private Boolean deleted;
    @JoinColumn(name = "subexamId", referencedColumnName = "id")
    @ManyToOne
    private Subexam subexamId;
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    @ManyToOne
    private Student studentId;

    public SubexamResult() {
    }

    public SubexamResult(Integer id) {
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

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Subexam getSubexamId() {
        return subexamId;
    }

    public void setSubexamId(Subexam subexamId) {
        this.subexamId = subexamId;
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
        if (!(object instanceof SubexamResult)) {
            return false;
        }
        SubexamResult other = (SubexamResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eEducation.ftn.model.SubexamResult[ id=" + id + " ]";
    }
    
}
