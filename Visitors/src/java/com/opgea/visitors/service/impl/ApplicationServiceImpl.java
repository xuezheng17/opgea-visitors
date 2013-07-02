/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.impl;

import com.opgea.visitors.dao.util.HibernateUtil;
import com.opgea.visitors.domain.entities.Company;
import com.opgea.visitors.domain.entities.Visitor;
import com.opgea.visitors.domain.model.CompanyStatus;
import com.opgea.visitors.domain.model.EmployeeStatus;
import com.opgea.visitors.domain.model.VisitorStatus;
import com.opgea.visitors.domain.qualifier.OnlineQualifier;
import com.opgea.visitors.domain.qualifier.RequestStatusQualifier;
import com.opgea.visitors.service.ApplicationService;
import com.opgea.visitors.web.dto.VisitorDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class ApplicationServiceImpl implements ApplicationService{

    private Map<Long, CompanyStatus> companies =
            new HashMap<Long, CompanyStatus>();

    
    public ApplicationServiceImpl(){
        System.out.println("Application Service Constructor");
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Company> companyList = session.createQuery("from Company").list();
        for(Company company : companyList){
            CompanyStatus companyStatus = new CompanyStatus(company.getId(),
                    company.getCompanyName());
            companies.put(companyStatus.getCompanyId(), companyStatus);
        }
        
        List<Visitor> visitorList = session.createQuery("from Visitor v where v.outTime is null").list();
        List<VisitorStatus> visitorStatusList = new ArrayList<VisitorStatus>();
        for(Visitor visitor: visitorList){
            VisitorStatus visitorStatus = new VisitorStatus();
            visitorStatus.setCompanyId(visitor.getCompany().getId());
            visitorStatus.setVisitorId(visitor.getId());
            visitorStatus.setVisitorName(visitor.getName());
            visitorStatus.setContactNo(visitor.getContactNo());
            visitorStatus.setPurpose(visitor.getPurpose());
            visitorStatus.setRequestStatus(visitor.getStatus());
            if(visitor.getForwardedTo() != null){
                visitorStatus.setForwardedTo(visitor.getForwardedTo().getId());
            }
            visitorStatus.setStatusRead(Boolean.TRUE);
            
            if(companies.containsKey(visitorStatus.getCompanyId())){
                CompanyStatus companyStatus = companies.get(visitorStatus.getCompanyId());
                companyStatus.getVisitors().put(visitorStatus.getVisitorId(), visitorStatus);
            }
        }
    }
    
    
    /*
     * Company Autorization and Subcription of Product Status
     */
    @Override
    public CompanyStatus addCompanyStatus(CompanyStatus companyStatus) {
        if(companies.containsKey(companyStatus.getCompanyId())){
        }else{
            companies.put(companyStatus.getCompanyId(), companyStatus);
        }
        return companyStatus;
    }

    @Override
    public CompanyStatus removeCompanyStatus(Long companyId) {
        CompanyStatus companyStatus = null;
        if(companies.containsKey(companyStatus.getCompanyId())){
            companyStatus = companies.get(companyId);
            companies.remove(companyId);
        }
        return companyStatus;
    }

    @Override
    public CompanyStatus updateCompanyStatus(CompanyStatus companyStatus) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Map<Long, CompanyStatus> findAllCompanies() {
        return this.companies;
    }

    @Override
    public CompanyStatus findCompany(Long id) {
        CompanyStatus companyStatus = companies.get(id);
        return companyStatus;
    }
    
    /*
     * Employee OnlineStatus
     */
    
    @Override
    public EmployeeStatus addEmployeeStatus(EmployeeStatus employeeStatus) {
        if(companies.containsKey(employeeStatus.getCompanyId())){
            CompanyStatus companyStatus = companies.get(employeeStatus.getCompanyId());
            
            if(companyStatus.getEmployees().containsKey(employeeStatus.getEmployeeId())){
                
            }else{
                companyStatus.getEmployees().put(employeeStatus.getEmployeeId(), employeeStatus);
            }
        }
        return employeeStatus;
    }

    @Override
    public EmployeeStatus removeEmployeeStatus(EmployeeStatus employeeStatus) {
        EmployeeStatus employeeSts = null;
        if(companies.containsKey(employeeStatus.getCompanyId())){
            CompanyStatus companyStatus = companies.get(employeeStatus.getCompanyId());
            if(companyStatus.getEmployees().containsKey(employeeStatus.getEmployeeId())){
                employeeSts = companyStatus.getEmployees().get(employeeStatus.getEmployeeId());
                companyStatus.getEmployees().remove(employeeStatus.getEmployeeId());
            }
        }
        return employeeSts;
    }

    @Override
    public EmployeeStatus updateEmployeeStatus(EmployeeStatus employeeStatus) {
        EmployeeStatus employeeSts = null;
        if(companies.containsKey(employeeStatus.getCompanyId())){
            CompanyStatus companyStatus = companies.get(employeeStatus.getCompanyId());
            if(companyStatus.getEmployees().containsKey(employeeStatus.getEmployeeId())){
                employeeSts = companyStatus.getEmployees().get(employeeStatus.getEmployeeId());
                employeeSts.setOnlineStatus(employeeStatus.getOnlineStatus());
                //companyStatus.getEmployees().remove(employeeStatus.getEmployeeId());
            }
        }
        return employeeSts;
    }

    @Override
    public OnlineQualifier findEmployeeOnlineStatus(EmployeeStatus employeeStatus) {
        OnlineQualifier onlineStatus = OnlineQualifier.OFFLINE;
        EmployeeStatus employeeSts = null;
        if(companies.containsKey(employeeStatus.getCompanyId())){
            CompanyStatus companyStatus = companies.get(employeeStatus.getCompanyId());
            if(companyStatus.getEmployees().containsKey(employeeStatus.getEmployeeId())){
                employeeSts = companyStatus.getEmployees().get(employeeStatus.getEmployeeId());
                onlineStatus = employeeSts.getOnlineStatus();
            }
        }
        return onlineStatus;
    }

    @Override
    public List<EmployeeStatus> findAllEmployeeStatus(Long companyId) {
        List<EmployeeStatus> employees = null;
        if(companies.containsKey(companyId)){
           CompanyStatus companyStatus = companies.get(companyId);
           employees = new ArrayList<EmployeeStatus>(companyStatus.getEmployees().values());
           
        }
        return employees;
    }

    
    /*
     * Visitor Status
     */
    
    @Override
    public VisitorStatus addVisitorStatus(VisitorStatus visitorStatus) {
        if(companies.containsKey(visitorStatus.getCompanyId())){
            CompanyStatus companyStatus = companies.get(visitorStatus.getCompanyId());
            if(companyStatus.getVisitors().containsKey(visitorStatus.getVisitorId())){
                
            }else{
                companyStatus.getVisitors().put(visitorStatus.getVisitorId(), visitorStatus);
                //companyStatus.getVisitors().get(visitorStatus.getVisitorId());
            }
        }
        return visitorStatus;
    }

    @Override
    public VisitorStatus removeVisitorStatus(VisitorStatus visitorStatus) {
        VisitorStatus visitorSts = null;
        if(companies.containsKey(visitorStatus.getCompanyId())){
            CompanyStatus companyStatus = companies.get(visitorStatus.getCompanyId());
            if(companyStatus.getVisitors().containsKey(visitorStatus.getVisitorId())){
                visitorSts = companyStatus.getVisitors().get(visitorStatus.getVisitorId());
                companyStatus.getVisitors().remove(visitorStatus.getVisitorId());
            }
        }
        return visitorSts;
    }

    @Override
    public VisitorStatus updateVisitorStatus(VisitorStatus visitorStatus) {
        VisitorStatus visitorSts = null;
        if(companies.containsKey(visitorStatus.getCompanyId())){
            CompanyStatus companyStatus = companies.get(visitorStatus.getCompanyId());
            if(companyStatus.getVisitors().containsKey(visitorStatus.getVisitorId())){
                visitorSts = companyStatus.getVisitors().get(visitorStatus.getVisitorId());
                visitorSts.setRequestStatus(visitorStatus.getRequestStatus());
                visitorSts.setForwardedTo(visitorStatus.getForwardedTo());
                visitorSts.setStatusRead(visitorStatus.getStatusRead());
                visitorSts.setRespondedTime(System.currentTimeMillis());
            }else{
                companyStatus.getVisitors().put(visitorStatus.getVisitorId(), visitorStatus);
            }
        }
        return visitorStatus;
    }

    @Override
    public List<VisitorStatus> findAllVisitorStatus(Long companyId) {
        List<VisitorStatus> visitors = null;
        if(companies.containsKey(companyId)){
           CompanyStatus companyStatus = companies.get(companyId);
           visitors = new ArrayList<VisitorStatus>(companyStatus.getVisitors().values());
        }
        return visitors;
    }

    @Override
    public VisitorStatus findVisitorRequestStatus(VisitorStatus visitorStatus) {
        VisitorStatus visitorSts = null;
        if(companies.containsKey(visitorStatus.getCompanyId())){
            CompanyStatus companyStatus = companies.get(visitorStatus.getCompanyId());
            if(companyStatus.getVisitors().containsKey(visitorStatus.getVisitorId())){
                visitorSts = companyStatus.getVisitors().get(visitorStatus.getVisitorId());
            }
        }
        return visitorSts;
    }

    @Override
    public List<VisitorStatus> findAllVisitorStatusByEmployeeId(Long companyId, Long employeeId) {
        List<VisitorStatus> visitors = new ArrayList<VisitorStatus>();
        if(companies.containsKey(companyId)){
           List<VisitorStatus> allVisitors = null; //All visitors of a particular company
           CompanyStatus companyStatus = companies.get(companyId);
           allVisitors = new ArrayList<VisitorStatus>(companyStatus.getVisitors().values());
           System.out.println("All visitorSize: "+allVisitors.size());
           for(VisitorStatus  visitorStatus : allVisitors){
               if(visitorStatus.getForwardedTo().equals(employeeId)){
                   visitors.add(visitorStatus);
               }
           }
           //System.out.println("No. of visitor Status : "+allVisitors.size());
        }
        return visitors;
    }
    
    @Override
    public VisitorStatus findRespondedVisitorStatusByEmployeeId(Long companyId, Long employeeId) {
        //List<VisitorStatus> visitors = new ArrayList<VisitorStatus>();
        VisitorStatus visitorState = new VisitorStatus();
        Long currentRespondeedTime = 0L;
        if(companies.containsKey(companyId)){
           List<VisitorStatus> allVisitors = null; //All visitors of a particular company
           CompanyStatus companyStatus = companies.get(companyId);
           allVisitors = new ArrayList<VisitorStatus>(companyStatus.getVisitors().values());
           for(VisitorStatus  visitorStatus : allVisitors){
               if(visitorStatus.getForwardedTo().equals(employeeId) && visitorStatus.getRespondedTime() != null){
                   //visitors.add(visitorStatus);
                   if(visitorStatus.getRespondedTime() > currentRespondeedTime){
                       currentRespondeedTime = visitorStatus.getRespondedTime();
                       visitorState = visitorStatus;
                   }
               }
           }
           //System.out.println("No. of visitor Status : "+allVisitors.size());
        }
        return visitorState;
    }

    @Override
    public VisitorStatus findVisitorRequestStatus(Long companyId, Long visitorId) {
        VisitorStatus visitorSts = null;
        if(companies.containsKey(companyId)){
            CompanyStatus companyStatus = companies.get(companyId);
            if(companyStatus.getVisitors().containsKey(visitorId)){
                visitorSts = companyStatus.getVisitors().get(visitorId);
            }
        }
        return visitorSts;
    }

    @Override
    public VisitorDTO updateVisitorStatus(VisitorDTO visitorDTO) {
        
        VisitorStatus visitorStatus = new VisitorStatus();
        if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.NEW_REQUEST.toString())){
            System.out.println("ApplicationService >> Visitor: NEW : "+visitorDTO.getCompanyId()+" | "+visitorDTO.getId());
            visitorStatus.setCompanyId(visitorDTO.getCompanyId());
            visitorStatus.setVisitorId(visitorDTO.getId());
            visitorStatus.setRequestStatus(RequestStatusQualifier.NEW_REQUEST);
            visitorStatus.setForwardedTo(visitorDTO.getEmployeeId());
            //visitorStatus.setRespondedTime(System.currentTimeMillis());
            this.addVisitorStatus(visitorStatus);
        }
        if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.CHECK_IN.toString())){
            visitorStatus = this.findVisitorRequestStatus(new VisitorStatus(visitorDTO.getCompanyId(), visitorDTO.getId()));
            visitorStatus.setForwardedTo(visitorDTO.getEmployeeId());
            //visitorStatus.setRespondedTime(System.currentTimeMillis());
        }
        if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.CHECK_OUT.toString())){
            //visitorStatus = this.findVisitorRequestStatus(new VisitorStatus(visitorDTO.getCompanyId(), visitorDTO.getId()));
            this.removeVisitorStatus(new VisitorStatus(visitorDTO.getCompanyId(), visitorDTO.getId()));
            /*
             * Need not to set the RequestQualifierStatus because
             * this Visitor is removed from the ApplicationContext
             * at this stage.
             */
            //visitorStatus.setForwardedTo(new Long(0));
        }
        if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.CAN_NOT_MEET.toString())){
            //visitorStatus = this.findVisitorRequestStatus(new VisitorStatus(visitorDTO.getCompanyId(), visitorDTO.getId()));
            this.removeVisitorStatus(new VisitorStatus(visitorDTO.getCompanyId(), visitorDTO.getId()));
            /*
             * Need not to set the RequestQualifierStatus because
             * this Visitor is removed from the ApplicationContext
             * at this stage.
             */
            //visitorStatus.setForwardedTo(new Long(0));
        }
        /*
         * Action listed below are being forwarded back to Reception(createdBy)
         */
        if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.WAIT.toString())){
            visitorStatus = this.findVisitorRequestStatus(new VisitorStatus(visitorDTO.getCompanyId(), visitorDTO.getId()));
            visitorStatus.setForwardedTo(visitorDTO.getCreatedBy());
            //visitorStatus.setRespondedTime(System.currentTimeMillis());
        }
        if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.CALL.toString())){
            visitorStatus = this.findVisitorRequestStatus(new VisitorStatus(visitorDTO.getCompanyId(), visitorDTO.getId()));
            visitorStatus.setForwardedTo(visitorDTO.getCreatedBy());
            //visitorStatus.setRespondedTime(System.currentTimeMillis());
        }
        if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.MEETING_DONE.toString())){
            visitorStatus = this.findVisitorRequestStatus(new VisitorStatus(visitorDTO.getCompanyId(), visitorDTO.getId()));
            visitorStatus.setForwardedTo(visitorDTO.getCreatedBy());
            //visitorStatus.setRespondedTime(System.currentTimeMillis());
        }
        if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.NOT_INTERESTED.toString())){
            visitorStatus = this.findVisitorRequestStatus(new VisitorStatus(visitorDTO.getCompanyId(), visitorDTO.getId()));
            visitorStatus.setForwardedTo(visitorDTO.getCreatedBy());
            //visitorStatus.setRespondedTime(System.currentTimeMillis());
        }
        
        visitorStatus.setVisitorName(visitorDTO.getName());
        visitorStatus.setContactNo(visitorDTO.getContactNo());
        visitorStatus.setPurpose(visitorDTO.getPurpose());
        visitorStatus.setRequestStatus(RequestStatusQualifier.valueOf(RequestStatusQualifier.class, visitorDTO.getStatusString()));
        visitorStatus.setStatusRead(Boolean.FALSE);
        visitorStatus.setRespondedTime(System.currentTimeMillis());
        this.updateVisitorStatus(visitorStatus);
        
        return visitorDTO;
    }
    
    
    
}
