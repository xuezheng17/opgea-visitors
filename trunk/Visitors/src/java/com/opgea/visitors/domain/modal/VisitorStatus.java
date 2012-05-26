/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.domain.modal;

import com.opgea.visitors.domain.qualifier.RequestStatusQualifier;

/**
 *
 * @author Ramesh
 */
public class VisitorStatus {
    
    private Long companyId;
    private Long visitorId;
    private String visitorName;
    private String contactNo;
    private String purpose;
    private RequestStatusQualifier requestStatus;
    private Long forwardedTo; //Employee Id. It could be either Reception,Employee or Admin
    private Boolean statusRead; //To insure that notification should not be repeatitive
    private Long respondedTime;

    public VisitorStatus() {
    }

    public VisitorStatus(Long companyId, Long visitorId) {
        this.companyId = companyId;
        this.visitorId = visitorId;
    }

    public VisitorStatus(Long companyId, Long visitorId, String visitorName, String contactNo, String purpose, RequestStatusQualifier requestStatus, Long forwardedTo, Boolean statusRead) {
        this.companyId = companyId;
        this.visitorId = visitorId;
        this.visitorName = visitorName;
        this.contactNo = contactNo;
        this.purpose = purpose;
        this.requestStatus = requestStatus;
        this.forwardedTo = forwardedTo;
        this.statusRead = statusRead;
    }

    

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public RequestStatusQualifier getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatusQualifier requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public Long getForwardedTo() {
        return forwardedTo;
    }

    public void setForwardedTo(Long forwardedTo) {
        this.forwardedTo = forwardedTo;
    }

    public Boolean getStatusRead() {
        return statusRead;
    }

    public void setStatusRead(Boolean statusRead) {
        this.statusRead = statusRead;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Long getRespondedTime() {
        return respondedTime;
    }

    public void setRespondedTime(Long respondedTime) {
        this.respondedTime = respondedTime;
    }

    @Override
    public String toString() {
        return "VisitorStatus{" + "companyId=" + companyId + ", visitorId=" + visitorId + ", visitorName=" + visitorName + ", contactNo=" + contactNo + ", purpose=" + purpose + ", requestStatus=" + requestStatus + ", forwardedTo=" + forwardedTo + ", statusRead=" + statusRead + '}';
    }

    
    
    
}
