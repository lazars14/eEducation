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
    @NamedQuery(name = "StudentExamEntry.findById", query = "SELECT s FROM StudentExamEntry s WHERE s.id = :id"),
    @NamedQuery(name = "StudentExamEntry.findByEDate", query = "SELECT s FROM StudentExamEntry s WHERE s.eDate = :eDate")})
public class StudentExamEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "eDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eDate;
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    @ManyToOne
    private Student studentId;
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    @ManyToOne
    private Course courseId;
    @JoinColumn(name = "examPeriodId", referencedColumnName = "id")
    @ManyToOne
    private ExamPeriod examPeriodId;

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

    public Date getEDate() {
        return eDate;
    }

    public void setEDate(Date eDate) {
        this.eDate = eDate;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public ExamPeriod getExamPeriodId() {
        return examPeriodId;
    }

    public void setExamPeriodId(ExamPeriod examPeriodId) {
        this.examPeriodId = examPeriodId;
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
