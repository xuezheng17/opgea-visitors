/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.dto;

import java.util.Date;

/**
 *
 * @author Ramesh
 */
public class VisitorDTO {


    private Long id;
    private String name;
    private String contactNo;
    private String address;
    private String purpose;
    private String fromCompany;
    private Integer status;
    private String statusString;
    private Date visitingDate;
    private String inTime;
    private String outTime;
    private Long employeeId;
    private String employeeName;
    private Long companyId;
    private String companyName;
    private byte[] picture;
    private Long createdBy;
    private String forwardedToMe;
    private String metaData;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getFromCompany() {
        return fromCompany;
    }

    public void setFromCompany(String fromCompany) {
        this.fromCompany = fromCompany;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(Date visitingDate) {
        this.visitingDate = visitingDate;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getForwardedToMe() {
        return forwardedToMe;
    }

    public void setForwardedToMe(String forwardedToMe) {
        this.forwardedToMe = forwardedToMe;
    }

    public String getMetaData() {
        StringBuilder data = new StringBuilder();
        data.append(this.name);
        data.append(" ");
        data.append(this.fromCompany);
        data.append(" ");
        data.append(this.contactNo);
        data.append(" ");
        data.append(this.visitingDate);
        data.append(" ");
        data.append(this.address);
        data.append(" ");
        data.append(this.purpose);
        this.metaData = data.toString();
        return metaData;
    }

    /*
    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }*/

    @Override
    public String toString() {
        return "VisitorDTO{" + "id=" + id + ", name=" + name + ", contactNo=" + contactNo + ", address=" + address + ", purpose=" + purpose + ", fromCompany=" + fromCompany + ", status=" + status + ", statusString=" + statusString + ", visitingDate=" + visitingDate + ", inTime=" + inTime + ", outTime=" + outTime + ", employeeId=" + employeeId + ", employeeName=" + employeeName + ", companyId=" + companyId + ", companyName=" + companyName + ", picture=" + picture + ", createdBy=" + createdBy + ", forwardedToMe=" + forwardedToMe + '}';
    }

    

    
    

}
