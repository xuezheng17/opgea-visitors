/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.impl;

import com.opgea.util.DateUtil;
import com.opgea.visitors.dao.CompanyDAO;
import com.opgea.visitors.dao.EmployeeDAO;
import com.opgea.visitors.dao.VisitorDAO;
import com.opgea.visitors.domain.entities.Company;
import com.opgea.visitors.domain.entities.Employee;
import com.opgea.visitors.domain.entities.Visitor;
import com.opgea.visitors.domain.model.VisitorStatus;
import com.opgea.visitors.domain.qualifier.ReplyQualifier;
import com.opgea.visitors.domain.qualifier.RequestStatusQualifier;
import com.opgea.visitors.service.ApplicationService;
import com.opgea.visitors.service.VisitorService;
import com.opgea.visitors.web.dto.VisitorDTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class VisitorServiceImpl implements VisitorService{

    @Autowired
    private VisitorDAO visitorDAO;
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private ApplicationService applicationService;
    
    @Override
    public VisitorDTO create(VisitorDTO visitorDTO) {
        Company company = companyDAO.find(visitorDTO.getCompanyId());
        Employee employee = employeeDAO.find(visitorDTO.getEmployeeId());
        Employee createdBy = employeeDAO.find(visitorDTO.getCreatedBy());
        Visitor visitor = null;
        if(visitorDTO.getId() == 0){
            visitor = new Visitor();
        }else{
            visitor = visitorDAO.find(visitorDTO.getId());
        }
        visitor.setName(visitorDTO.getName());
        visitor.setAddress(visitorDTO.getAddress());
        visitor.setContactNo(visitorDTO.getContactNo());
        visitor.setFromCompany(visitorDTO.getFromCompany());
        visitor.setPurpose(visitorDTO.getPurpose()); 
        visitor.setVisitingDate(Calendar.getInstance().getTime());
        //visitor.setInTime(System.currentTimeMillis());
        
        visitor.setCompany(company);
        visitor.setEmployee(employee);
        visitor.setCreatedBy(visitorDTO.getCreatedBy());
        visitor.setMetaData(visitorDTO.getMetaData());
        
        if(visitorDTO.getId() == 0){
            visitor.setStatus(RequestStatusQualifier.NEW_REQUEST);
            /*
             * Image can only set to Visitor entity when first time record
             * is being saved to database. Image will not be updated
             * during the RECEPTION and EMPLOYEE communication.
             */
            visitor.setPicture(visitorDTO.getPicture());
            visitor.setForwardedTo(employee);
            visitorDAO.create(visitor);
            
            visitorDTO.setId(visitor.getId());
            visitorDTO.setStatus(RequestStatusQualifier.NEW_REQUEST.ordinal());
            visitorDTO.setStatusString(RequestStatusQualifier.NEW_REQUEST.toString());
            visitorDTO.setForwardedToMe(ReplyQualifier.YES.name());
            //applicationService.updateVisitorStatus(visitorDTO);
        }else{
            if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.CHECK_IN.toString())){
                visitor.setInTime(System.currentTimeMillis());
                visitor.setStatus(RequestStatusQualifier.CHECK_IN);
                visitor.setForwardedTo(employee);
                visitorDAO.update(visitor);
                
                visitorDTO.setInTime(String.valueOf(System.currentTimeMillis()));
                visitorDTO.setStatus(RequestStatusQualifier.CHECK_IN.ordinal());
                visitorDTO.setStatusString(RequestStatusQualifier.CHECK_IN.toString());
                //applicationService.updateVisitorStatus(visitorDTO);
            }
            if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.CHECK_OUT.toString())){
                visitor.setOutTime(System.currentTimeMillis());
                visitor.setStatus(RequestStatusQualifier.CHECK_OUT);
                visitor.setForwardedTo(createdBy);
                visitorDAO.update(visitor);
                
                visitorDTO.setOutTime(String.valueOf(System.currentTimeMillis()));
                visitorDTO.setStatus(RequestStatusQualifier.CHECK_OUT.ordinal());
                visitorDTO.setStatusString(RequestStatusQualifier.CHECK_OUT.toString());
                //applicationService.removeVisitorStatus(new VisitorStatus(visitorDTO.getCompanyId(), visitorDTO.getId()));
            }
            if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.CAN_NOT_MEET.toString())){
                visitor.setInTime(System.currentTimeMillis());
                visitor.setOutTime(System.currentTimeMillis());
                visitor.setStatus(RequestStatusQualifier.CAN_NOT_MEET);
                visitor.setForwardedTo(createdBy);
                visitorDAO.update(visitor);
                
                visitorDTO.setInTime(String.valueOf(System.currentTimeMillis()));
                visitorDTO.setOutTime(String.valueOf(System.currentTimeMillis()));
                visitorDTO.setStatus(RequestStatusQualifier.CAN_NOT_MEET.ordinal());
                visitorDTO.setStatusString(RequestStatusQualifier.CAN_NOT_MEET.toString());
                //applicationService.removeVisitorStatus(new VisitorStatus(visitorDTO.getCompanyId(), visitorDTO.getId()));
            }
            else{
                visitor.setStatus(RequestStatusQualifier.valueOf(RequestStatusQualifier.class, visitorDTO.getStatusString()));
                visitorDAO.update(visitor);
                
                visitorDTO.setStatus(RequestStatusQualifier.valueOf(visitorDTO.getStatusString()).ordinal());
                visitorDTO.setStatusString(RequestStatusQualifier.valueOf(visitorDTO.getStatusString()).name());
                //applicationService.updateVisitorStatus(visitorDTO);
            }
            visitorDAO.update(visitor);
        }
        
        applicationService.updateVisitorStatus(visitorDTO);
        return visitorDTO;        
    }
    
    @Override
    public VisitorDTO update(VisitorDTO visitorDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public VisitorDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public VisitorDTO find(Long id) {
        Visitor visitor = visitorDAO.find(id);
        
        VisitorDTO visitorDTO = new VisitorDTO();
        visitorDTO.setId(visitor.getId());
        visitorDTO.setName(visitor.getName());
        visitorDTO.setAddress(visitor.getAddress());
        visitorDTO.setFromCompany(visitor.getFromCompany());
        visitorDTO.setPurpose(visitor.getPurpose());
        visitorDTO.setContactNo(visitor.getContactNo());
        visitorDTO.setStatus(visitor.getStatus().ordinal());
        visitorDTO.setStatusString(visitor.getStatus().toString());
        visitorDTO.setVisitingDate(visitor.getVisitingDate());
        visitorDTO.setCreatedBy(visitor.getCreatedBy());
        visitorDTO.setPicture(visitor.getPicture());
        if(visitor.getCompany() != null){
            Company company = visitor.getCompany();
            visitorDTO.setCompanyId(company.getId());
            visitorDTO.setCompanyName(company.getCompanyName());
        }
        if(visitor.getEmployee() != null){
            Employee employee = visitor.getEmployee();
            visitorDTO.setEmployeeId(employee.getId());
            visitorDTO.setEmployeeName(employee.getFirstName());
        }
        if(visitor.getInTime() != null){
                visitorDTO.setInTime(DateUtil.getHHmmss(visitor.getInTime()));
            }
        if(visitor.getOutTime() != null){
            visitorDTO.setOutTime(DateUtil.getHHmmss(visitor.getOutTime()));
        }
        return visitorDTO;
    }

    @Override
    public List<VisitorDTO> findAll() {
        List<Visitor> visitors = visitorDAO.findAll();
        List<VisitorDTO> visitorList = new ArrayList<VisitorDTO>();
        for(Visitor visitor: visitors){
            VisitorDTO visitorDTO = new VisitorDTO();
            visitorDTO.setId(visitor.getId());
            visitorDTO.setName(visitor.getName());
            visitorDTO.setAddress(visitor.getAddress());
            visitorDTO.setFromCompany(visitor.getFromCompany());
            visitorDTO.setPurpose(visitor.getPurpose());
            visitorDTO.setContactNo(visitor.getContactNo());
            visitorDTO.setStatus(visitor.getStatus().ordinal());
            visitorDTO.setStatusString(visitor.getStatus().toString());
            visitorDTO.setVisitingDate(visitor.getVisitingDate());
            visitorDTO.setCreatedBy(visitor.getCreatedBy());
            visitorDTO.setPicture(visitor.getPicture());
            if(visitor.getCompany() != null){
                Company company = visitor.getCompany();
                visitorDTO.setCompanyId(company.getId());
                visitorDTO.setCompanyName(company.getCompanyName());
            }
            if(visitor.getEmployee() != null){
                Employee employee = visitor.getEmployee();
                visitorDTO.setEmployeeId(employee.getId());
                visitorDTO.setEmployeeName(employee.getFirstName());
            }
            if(visitor.getInTime() != null){
                visitorDTO.setInTime(DateUtil.getHHmmss(visitor.getInTime()));
            }
            if(visitor.getOutTime() != null){
                visitorDTO.setOutTime(DateUtil.getHHmmss(visitor.getOutTime()));
            }
            visitorList.add(visitorDTO);
        }
        return visitorList;
    }

    @Override
    public List<VisitorDTO> findAllByCompanyId(Long companyId, Long loggedUser) {
        List<Visitor> visitors = visitorDAO.findAllByCompanyId(companyId);
        List<VisitorDTO> visitorList = new ArrayList<VisitorDTO>();
        for(Visitor visitor: visitors){
            VisitorDTO visitorDTO = new VisitorDTO();
            visitorDTO.setId(visitor.getId());
            visitorDTO.setName(visitor.getName());
            visitorDTO.setAddress(visitor.getAddress());
            visitorDTO.setFromCompany(visitor.getFromCompany());
            visitorDTO.setPurpose(visitor.getPurpose());
            visitorDTO.setContactNo(visitor.getContactNo());
            visitorDTO.setStatus(visitor.getStatus().ordinal());
            visitorDTO.setStatusString(visitor.getStatus().toString());
            visitorDTO.setVisitingDate(visitor.getVisitingDate());
            visitorDTO.setCreatedBy(visitor.getCreatedBy());
            visitorDTO.setPicture(visitor.getPicture());
            if(visitor.getCompany() != null){
                Company company = visitor.getCompany();
                visitorDTO.setCompanyId(company.getId());
                visitorDTO.setCompanyName(company.getCompanyName());
            }
            if(visitor.getEmployee() != null){
                Employee employee = visitor.getEmployee();
                visitorDTO.setEmployeeId(employee.getId());
                visitorDTO.setEmployeeName(employee.getFirstName());
            }
            if(visitor.getInTime() != null){
                visitorDTO.setInTime(DateUtil.getHHmmss(visitor.getInTime()));
            }
            if(visitor.getOutTime() != null){
                visitorDTO.setOutTime(DateUtil.getHHmmss(visitor.getOutTime()));
            }
            
            VisitorStatus visitorStatus = applicationService.findVisitorRequestStatus(visitorDTO.getCompanyId(), visitorDTO.getId());
            if(visitorStatus != null){
                if(visitorStatus.getForwardedTo().equals(loggedUser)){
                    visitorDTO.setForwardedToMe(ReplyQualifier.YES.name());
                }else{
                    visitorDTO.setForwardedToMe(ReplyQualifier.NO.name());
                }
            }
            visitorList.add(visitorDTO);
        }
        return visitorList;
    }

    @Override
    public List<VisitorDTO> findAllByEmployeeId(Long employeeId, Long loggedUser) {
        List<Visitor> visitors = visitorDAO.findAllByEmployeeId(employeeId);
        List<VisitorDTO> visitorList = new ArrayList<VisitorDTO>();
        for(Visitor visitor: visitors){
            VisitorDTO visitorDTO = new VisitorDTO();
            visitorDTO.setId(visitor.getId());
            visitorDTO.setName(visitor.getName());
            visitorDTO.setAddress(visitor.getAddress());
            visitorDTO.setFromCompany(visitor.getFromCompany());
            visitorDTO.setPurpose(visitor.getPurpose());
            visitorDTO.setContactNo(visitor.getContactNo());
            visitorDTO.setStatus(visitor.getStatus().ordinal());
            visitorDTO.setStatusString(visitor.getStatus().toString());
            visitorDTO.setVisitingDate(visitor.getVisitingDate());
            //System.out.println("VisitorServiceImpl >> findAllByEmployeeId >> Created By: "+visitor.getCreatedBy());
            visitorDTO.setCreatedBy(visitor.getCreatedBy());
            visitorDTO.setPicture(visitor.getPicture());
            if(visitor.getCompany() != null){
                Company company = visitor.getCompany();
                visitorDTO.setCompanyId(company.getId());
                visitorDTO.setCompanyName(company.getCompanyName());
            }
            if(visitor.getEmployee() != null){
                Employee employee = visitor.getEmployee();
                visitorDTO.setEmployeeId(employee.getId());
                visitorDTO.setEmployeeName(employee.getFirstName());
            }
            if(visitor.getInTime() != null){
                visitorDTO.setInTime(DateUtil.getHHmmss(visitor.getInTime()));
            }
            if(visitor.getOutTime() != null){
                visitorDTO.setOutTime(DateUtil.getHHmmss(visitor.getOutTime()));
            }
            VisitorStatus visitorStatus = applicationService.findVisitorRequestStatus(visitorDTO.getCompanyId(), visitorDTO.getId());
            if(visitorStatus != null){
                if(visitorStatus.getForwardedTo().equals(loggedUser)){
                    visitorDTO.setForwardedToMe(ReplyQualifier.YES.name());
                }else{
                    visitorDTO.setForwardedToMe(ReplyQualifier.NO.name());
                }
            }
            visitorList.add(visitorDTO);
        }
        return visitorList;
    }

    public List<VisitorDTO> searchVisitors(Long companyId, Long employeeId, String visitingDate, String searchKey) {
        List<Visitor> visitors = visitorDAO.searchVisitors(companyId, employeeId, visitingDate, searchKey);
        List<VisitorDTO> visitorList = new ArrayList<VisitorDTO>();
        for(Visitor visitor: visitors){
            VisitorDTO visitorDTO = new VisitorDTO();
            visitorDTO.setId(visitor.getId());
            visitorDTO.setName(visitor.getName());
            visitorDTO.setAddress(visitor.getAddress());
            visitorDTO.setFromCompany(visitor.getFromCompany());
            visitorDTO.setPurpose(visitor.getPurpose());
            visitorDTO.setContactNo(visitor.getContactNo());
            visitorDTO.setStatus(visitor.getStatus().ordinal());
            visitorDTO.setStatusString(visitor.getStatus().toString());
            visitorDTO.setVisitingDate(visitor.getVisitingDate());
            //System.out.println("VisitorServiceImpl >> findAllByEmployeeId >> Created By: "+visitor.getCreatedBy());
            visitorDTO.setCreatedBy(visitor.getCreatedBy());
            if(visitor.getPicture() != null){
                visitorDTO.setPicture(visitor.getPicture());
            }
            if(visitor.getCompany() != null){
                Company company = visitor.getCompany();
                visitorDTO.setCompanyId(company.getId());
                visitorDTO.setCompanyName(company.getCompanyName());
            }
            if(visitor.getEmployee() != null){
                Employee employee = visitor.getEmployee();
                visitorDTO.setEmployeeId(employee.getId());
                visitorDTO.setEmployeeName(employee.getFirstName());
            }
            if(visitor.getInTime() != null){
                visitorDTO.setInTime(DateUtil.getHHmmss(visitor.getInTime()));
            }
            if(visitor.getOutTime() != null){
                visitorDTO.setOutTime(DateUtil.getHHmmss(visitor.getOutTime()));
            }
            visitorList.add(visitorDTO);
        }
        return visitorList;
    }

    
}
