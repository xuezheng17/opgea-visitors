/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.domain.model;

/**
 *
 * @author Ramesh
 */
public class SmsModel {

    
    private Long visitorId;
    private String visitorName;
    private String visitorContact;
    private String visitorFrom;
    private String purpose;
    
    private String toName;
    private String toPhoneNo; //String because multiple phone no. can be used by comma seperated
    private String from; //from is for OPGEA
    private String message;

    public SmsModel() {
    }

    public SmsModel(Long visitorId, String visitorName, String visitorContact, String visitorFrom, String purpose, String toName,  String toPhoneNo, String from, String message) {
        this.visitorId = visitorId;
        this.visitorName = visitorName;
        this.visitorContact = visitorContact;
        this.visitorFrom = visitorFrom;
        this.purpose = purpose;
        this.toName = toName;
        this.toPhoneNo = toPhoneNo;
        this.from = from;
        this.message = message;
    }

    
    
    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToPhoneNo() {
        return toPhoneNo;
    }

    public void setToPhoneNo(String toPhoneNo) {
        this.toPhoneNo = toPhoneNo;
    }

    public String getVisitorContact() {
        return visitorContact;
    }

    public void setVisitorContact(String visitorContact) {
        this.visitorContact = visitorContact;
    }

    public String getVisitorFrom() {
        return visitorFrom;
    }

    public void setVisitorFrom(String visitorFrom) {
        this.visitorFrom = visitorFrom;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SmsModel other = (SmsModel) obj;
        if (this.visitorId != other.visitorId && (this.visitorId == null || !this.visitorId.equals(other.visitorId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.visitorId != null ? this.visitorId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "SmsModel{" + "visitorId=" + visitorId + ", visitorName=" + visitorName + ", visitorContact=" + visitorContact + ", visitorFrom=" + visitorFrom + ", purpose=" + purpose + ", toName=" + toName + ", toPhoneNo=" + toPhoneNo + ", from=" + from + ", message=" + message + '}';
    }
    
    
    
    
    
}
