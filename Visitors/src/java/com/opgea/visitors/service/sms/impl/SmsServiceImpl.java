/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.sms.impl;

import com.opgea.visitors.dao.VisitorDAO;
import com.opgea.visitors.domain.entities.Employee;
import com.opgea.visitors.domain.entities.Visitor;
import com.opgea.visitors.domain.model.SmsModel;
import com.opgea.visitors.service.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class SmsServiceImpl implements SmsService{

    @Autowired
    private VisitorDAO visitorDAO;

    public SmsModel getSmsModel(Long visitorId) {
        Visitor visitor = visitorDAO.find(visitorId);
        Employee employee = visitor.getEmployee();
                
        SmsModel smsModel = new SmsModel();
        smsModel.setVisitorId(visitor.getId());
        smsModel.setVisitorName(visitor.getName());
        smsModel.setVisitorContact(visitor.getContactNo());
        smsModel.setVisitorFrom(visitor.getFromCompany());
        
        smsModel.setToPhoneNo(employee.getContactNo());
        smsModel.setToName(employee.getFirstName()+" "+employee.getMiddleInitial()+" "+employee.getLastName());
        
        
        smsModel.setFrom("ACL");
        smsModel.setMessage("New Notification");
        return smsModel;
    }
    
}
