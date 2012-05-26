/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.domain.entities;

import com.opgea.visitors.domain.qualifier.RequestStatusQualifier;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "visitor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visitor.findAll", query = "SELECT v FROM Visitor v"),
    @NamedQuery(name = "Visitor.findById", query = "SELECT v FROM Visitor v WHERE v.id = :id"),
    @NamedQuery(name = "Visitor.findAllByNameLike", query = "SELECT v FROM Visitor v WHERE v.name like :name"),
    @NamedQuery(name = "Visitor.findAllByEmployeeId", query = "SELECT v FROM Visitor v WHERE v.outTime is null AND v.employee.id = :employeeId"),
    @NamedQuery(name = "Visitor.findAllByCompanyId", query = "SELECT v FROM Visitor v WHERE v.outTime is null AND v.company.id = :companyId")
    }
)
public class Visitor implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String contactNo;
    private String address;
    private String fromCompany;
    private String purpose;
    private RequestStatusQualifier status;
    @Temporal(TemporalType.DATE)
    private Date visitingDate;
    //@Temporal(TemporalType.DATE)
    private Long inTime;
    //@Temporal(TemporalType.DATE)
    private Long outTime;
    private String metaData;
    @Lob
    @Column(nullable = true, length = 2097152)
    private byte[] picture;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Company company;
    
    private Long createdBy;
    

    public Visitor() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public String getFromCompany() {
        return fromCompany;
    }

    public void setFromCompany(String fromCompany) {
        this.fromCompany = fromCompany;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Long getInTime() {
        return inTime;
    }

    public void setInTime(Long inTime) {
        this.inTime = inTime;
    }

    public Long getOutTime() {
        return outTime;
    }

    public void setOutTime(Long outTime) {
        this.outTime = outTime;
    }

    public RequestStatusQualifier getStatus() {
        return status;
    }

    public void setStatus(RequestStatusQualifier status) {
        this.status = status;
    }

    public Date getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(Date visitingDate) {
        this.visitingDate = visitingDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Visitor other = (Visitor) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Visitor{" + "id=" + id + ", name=" + name + ", contactNo=" + contactNo + ", address=" + address + ", purpose=" + purpose + ", employee=" + employee + '}';
    }
    
    
    
}
