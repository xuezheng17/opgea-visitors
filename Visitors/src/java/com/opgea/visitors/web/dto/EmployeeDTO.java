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
public class EmployeeDTO {
    
    
    private Long id;
    private Long companyId;
    private Integer employeeType;
    private String employeeTypeName;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private Date dateOfBirth;
    private Long branchId;
    
    private String email;
    private String phone1;
    private String phone2;
    private byte[] picture;
    
    private String empCode;
    private Long designationId;
    private String designationName;
    private Long departmentId;
    private String departmentName;
    private Integer onlineStatusId = 0;
    private String password = "";
    private String metaData;
    

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, Long companyId, Integer employeeType, String firstName, String middleInitial, String lastName, Date dateOfBirth, Long branchId, String email, String phone1, String phone2, byte[] picture, String empCode, Long designationId, String designationName, Long departmentId, String departmentName, Integer onlineStatusId, String password) {
        this.id = id;
        this.companyId = companyId;
        this.employeeType = employeeType;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.branchId = branchId;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.picture = picture;
        this.empCode = empCode;
        this.designationId = designationId;
        this.designationName = designationName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.onlineStatusId = onlineStatusId;
        this.password = password;
    }

    
    public Integer getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(Integer employeeType) {
        this.employeeType = employeeType;
    }


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Long designationId) {
        this.designationId = designationId;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEmployeeTypeName() {
        return employeeTypeName;
    }

    public void setEmployeeTypeName(String employeeTypeName) {
        this.employeeTypeName = employeeTypeName;
    }

    public Integer getOnlineStatusId() {
        return onlineStatusId;
    }

    public void setOnlineStatusId(Integer onlineStatusId) {
        this.onlineStatusId = onlineStatusId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMetaData() {
        StringBuilder metaBuilder = new StringBuilder();
        metaBuilder.append(this.firstName);
        metaBuilder.append(" ");
        metaBuilder.append(this.middleInitial);
        metaBuilder.append(" ");
        metaBuilder.append(this.lastName);
        metaBuilder.append(" ");
        metaBuilder.append(this.designationName);
        metaBuilder.append(" ");
        metaBuilder.append(this.departmentName);
        metaBuilder.append(" ");
        metaBuilder.append(this.phone1);
        metaBuilder.append(" ");
        metaBuilder.append(this.phone2);
        metaBuilder.append(" ");
        metaBuilder.append(this.email);
        this.metaData = metaBuilder.toString();
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    
    @Override
    public String toString() {
        return "EmployeeDTO{" + "id=" + id + ", companyId=" + companyId + ", employeeType=" + employeeType + ", employeeTypeName=" + employeeTypeName + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", branchId=" + branchId + ", email=" + email + ", phone1=" + phone1 + ", phone2=" + phone2 + ", picture=" + picture + ", empCode=" + empCode + ", designationId=" + designationId + ", designationName=" + designationName + ", departmentId=" + departmentId + ", departmentName=" + departmentName + ", onlineStatusId=" + onlineStatusId + '}';
    }



    
    
    
}
