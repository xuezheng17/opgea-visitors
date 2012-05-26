/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.domain.modal;

import com.opgea.visitors.domain.qualifier.OnlineQualifier;

/**
 *
 * @author Ramesh
 */
public class EmployeeStatus {
    
    private Long companyId;
    private Long employeeId;
    private OnlineQualifier onlineStatus;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public OnlineQualifier getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(OnlineQualifier onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "EmployeeStatus{" + "employeeId=" + employeeId + ", onlineStatus=" + onlineStatus + '}';
    }
    
    
    
    
}
