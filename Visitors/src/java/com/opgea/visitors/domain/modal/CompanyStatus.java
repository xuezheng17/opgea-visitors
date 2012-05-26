/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.domain.modal;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ramesh
 */
public class CompanyStatus {
    
    private Long companyId;
    private String name;
    private Map<Long, EmployeeStatus> employees = 
            new HashMap<Long, EmployeeStatus>();
    
    private Map<Long, VisitorStatus> visitors =
            new HashMap<Long, VisitorStatus>();

    public CompanyStatus(){}

    public CompanyStatus(Long companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }
    
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Long, EmployeeStatus> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<Long, EmployeeStatus> employees) {
        this.employees = employees;
    }

    public Map<Long, VisitorStatus> getVisitors() {
        return visitors;
    }

    public void setVisitors(Map<Long, VisitorStatus> visitors) {
        this.visitors = visitors;
    }

    @Override
    public String toString() {
        return "CompanyStatus{" + "companyId=" + companyId + ", name=" + name + '}';
    }
    
    
}
