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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lazar Stijakovic
 */
@Entity
@Table(name = "appUser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppUser.findAll", query = "SELECT a FROM AppUser a")
    , @NamedQuery(name = "AppUser.findById", query = "SELECT a FROM AppUser a WHERE a.id = :id")
    , @NamedQuery(name = "AppUser.findByFirstname", query = "SELECT a FROM AppUser a WHERE a.firstname = :firstname")
    , @NamedQuery(name = "AppUser.findByLastname", query = "SELECT a FROM AppUser a WHERE a.lastname = :lastname")
    , @NamedQuery(name = "AppUser.findByUsername", query = "SELECT a FROM AppUser a WHERE a.username = :username")
    , @NamedQuery(name = "AppUser.findByAPassword", query = "SELECT a FROM AppUser a WHERE a.aPassword = :aPassword")
    , @NamedQuery(name = "AppUser.findByRole", query = "SELECT a FROM AppUser a WHERE a.role = :role")
    , @NamedQuery(name = "AppUser.findByDeleted", query = "SELECT a FROM AppUser a WHERE a.deleted = :deleted")})
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 20)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 20)
    @Column(name = "username")
    private String username;
    @Size(max = 20)
    @Column(name = "aPassword")
    private String aPassword;
    @Size(max = 20)
    @Column(name = "role")
    private String role;
    @Column(name = "deleted")
    private Boolean deleted;

    public AppUser() {
    }

    public AppUser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAPassword() {
        return aPassword;
    }

    public void setAPassword(String aPassword) {
        this.aPassword = aPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eEducation.ftn.model.AppUser[ id=" + id + " ]";
    }
    
}
