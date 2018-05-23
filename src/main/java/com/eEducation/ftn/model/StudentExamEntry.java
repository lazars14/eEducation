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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lazar
 */
@Entity
@Table(name = "studentExamEntry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentExamEntry.findAll", query = "SELECT s FROM StudentExamEntry s"),
    @NamedQuery(name = "StudentExamEntry.findById", query = "SELECT s FROM StudentExamEntry s WHERE s.id = :id")
public class StudentExamEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    @ManyToOne
    private Student student;
    @JoinColumn(name = "examTermId", referencedColumnName = "id")
    @ManyToOne
    private ExamTerm examTerm;
    @JoinColumn(name = "gradeId", referencedColumnName = "id")
    @ManyToOne
    private Grade grade;

    public StudentExamEntry() {
    }

    public StudentExamEntry(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ExamTerm getExamTerm() {
        return examTerm;
    }

    public void setExamTerm(ExamTerm examTerm) {
        this.examTerm = examTerm;
    }
    
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
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
        if (!(object instanceof StudentExamEntry)) {
            return false;
        }
        StudentExamEntry other = (StudentExamEntry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.StudentExamEntry[ id=" + id + " ]";
    }
    
}
