/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eEducation.ftn.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lazar
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id"),
    @NamedQuery(name = "Student.findByIndexNumber", query = "SELECT s FROM Student s WHERE s.indexNumber = :indexNumber"),
    @NamedQuery(name = "Student.findByFirstname", query = "SELECT s FROM Student s WHERE s.firstname = :firstname"),
    @NamedQuery(name = "Student.findByLastname", query = "SELECT s FROM Student s WHERE s.lastname = :lastname"),
    @NamedQuery(name = "Student.findByAccountNumber", query = "SELECT s FROM Student s WHERE s.accountNumber = :accountNumber"),
    @NamedQuery(name = "Student.findByStudYear", query = "SELECT s FROM Student s WHERE s.studYear = :studYear"),
    @NamedQuery(name = "Student.findByStudYearOrdNum", query = "SELECT s FROM Student s WHERE s.studYearOrdNum = :studYearOrdNum"),
    @NamedQuery(name = "Student.findByEmail", query = "SELECT s FROM Student s WHERE s.email = :email"),
    @NamedQuery(name = "Student.findBySPassword", query = "SELECT s FROM Student s WHERE s.sPassword = :sPassword"),
    @NamedQuery(name = "Student.findByEspbPoints", query = "SELECT s FROM Student s WHERE s.espbPoints = :espbPoints")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "indexNumber")
    private String indexNumber;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "accountNumber")
    private String accountNumber;
    @Column(name = "studYear")
    private Integer studYear;
    @Column(name = "studYearOrdNum")
    private Integer studYearOrdNum;
    @Column(name = "email")
    private String email;
    @Column(name = "sPassword")
    private String sPassword;
    @Column(name = "espbPoints")
    private Integer espbPoints;
    @OneToMany(mappedBy = "studentId")
    private Collection<StudentExamEntry> studentExamEntryCollection;
    @JoinColumn(name = "classId", referencedColumnName = "id")
    @ManyToOne
    private Class classs;
    @OneToMany(mappedBy = "studentId")
    private Collection<StudentAttendsCourse> studentAttendsCourseCollection;
    @OneToMany(mappedBy = "studentId")
    private Collection<StudentDocument> studentDocumentCollection;
    @OneToMany(mappedBy = "studentId")
    private Collection<ColloquiumResult> colloquiumResultCollection;
    @OneToMany(mappedBy = "studentId")
    private Collection<Grade> gradeCollection;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getStudYear() {
        return studYear;
    }

    public void setStudYear(Integer studYear) {
        this.studYear = studYear;
    }

    public Integer getStudYearOrdNum() {
        return studYearOrdNum;
    }

    public void setStudYearOrdNum(Integer studYearOrdNum) {
        this.studYearOrdNum = studYearOrdNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSPassword() {
        return sPassword;
    }

    public void setSPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public Integer getEspbPoints() {
        return espbPoints;
    }

    public void setEspbPoints(Integer espbPoints) {
        this.espbPoints = espbPoints;
    }

    @XmlTransient
    public Collection<StudentExamEntry> getStudentExamEntryCollection() {
        return studentExamEntryCollection;
    }

    public void setStudentExamEntryCollection(Collection<StudentExamEntry> studentExamEntryCollection) {
        this.studentExamEntryCollection = studentExamEntryCollection;
    }

    public Class getClass() {
        return classs;
    }

    public void setClass(Class classs) {
        this.classs = classs;
    }

    @XmlTransient
    public Collection<StudentAttendsCourse> getStudentAttendsCourseCollection() {
        return studentAttendsCourseCollection;
    }

    public void setStudentAttendsCourseCollection(Collection<StudentAttendsCourse> studentAttendsCourseCollection) {
        this.studentAttendsCourseCollection = studentAttendsCourseCollection;
    }

    @XmlTransient
    public Collection<StudentDocument> getStudentDocumentCollection() {
        return studentDocumentCollection;
    }

    public void setStudentDocumentCollection(Collection<StudentDocument> studentDocumentCollection) {
        this.studentDocumentCollection = studentDocumentCollection;
    }

    @XmlTransient
    public Collection<ColloquiumResult> getColloquiumResultCollection() {
        return colloquiumResultCollection;
    }

    public void setColloquiumResultCollection(Collection<ColloquiumResult> colloquiumResultCollection) {
        this.colloquiumResultCollection = colloquiumResultCollection;
    }

    @XmlTransient
    public Collection<Grade> getGradeCollection() {
        return gradeCollection;
    }

    public void setGradeCollection(Collection<Grade> gradeCollection) {
        this.gradeCollection = gradeCollection;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.Student[ id=" + id + " ]";
    }
    
}
