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
    

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, Long companyId, String firstName, String middleInitial, String lastName, Date dateOfBirth, Long branchId, String email, String phone1, String phone2, String empCode, Long designationId, String designationName) {
        this.id = id;
        this.companyId = companyId;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.branchId = branchId;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.empCode = empCode;
        this.designationId = designationId;
        this.designationName = designationName;
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



    @Override
    public String toString() {
        return "EmployeeDTO{" + "id=" + id + ", companyId=" + companyId + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName=" + lastName + ", emailId=" + email + ", phone1=" + phone1 + ", phone2=" + phone2 + ", empCode=" + empCode + ", designationId=" + designationId + ", designationName=" + designationName + '}';
    }

    
    
    
}
