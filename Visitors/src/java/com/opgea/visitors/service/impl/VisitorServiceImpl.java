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
import com.opgea.visitors.domain.qualifier.RequestStatusQualifier;
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
    
    @Override
    public VisitorDTO create(VisitorDTO visitorDTO) {
        Company company = companyDAO.find(visitorDTO.getCompanyId());
        System.out.println("Company : "+company);
        Employee employee = employeeDAO.find(visitorDTO.getEmployeeId());
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
        
        if(visitorDTO.getId() == 0){
            visitor.setStatus(RequestStatusQualifier.NEW_REQUEST);
            visitorDAO.create(visitor);
        }else{
            visitorDAO.update(visitor);
        }
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
    public List<VisitorDTO> findAllByCompanyId(Long companyId) {
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
    public List<VisitorDTO> findAllByEmployeeId(Long employeeId) {
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
    public VisitorDTO checkInVisitor(VisitorDTO visitorDTO) {
        Visitor visitor = visitorDAO.find(visitorDTO.getId());
        visitor.setInTime(System.currentTimeMillis());
        visitor.setStatus(RequestStatusQualifier.CHECK_IN);
        visitorDAO.update(visitor);
        visitorDTO.setInTime(String.valueOf(System.currentTimeMillis()));
        visitorDTO.setStatus(RequestStatusQualifier.CHECK_IN.ordinal());
        visitorDTO.setStatusString(RequestStatusQualifier.CHECK_IN.toString());
        return visitorDTO;
    }

    @Override
    public VisitorDTO checkOutVisitor(VisitorDTO visitorDTO) {
        Visitor visitor = visitorDAO.find(visitorDTO.getId());
        visitor.setOutTime(System.currentTimeMillis());
        visitor.setStatus(RequestStatusQualifier.CHECK_OUT);
        visitorDAO.update(visitor);
        visitorDTO.setOutTime(String.valueOf(System.currentTimeMillis()));
        visitorDTO.setStatus(RequestStatusQualifier.CHECK_OUT.ordinal());
        visitorDTO.setStatusString(RequestStatusQualifier.CHECK_OUT.toString());
        return visitorDTO;
    }

    @Override
    public VisitorDTO updateVisitorStatus(VisitorDTO visitorDTO) {
        Visitor visitor = visitorDAO.find(visitorDTO.getId());
        visitor.setStatus(RequestStatusQualifier.valueOf(visitorDTO.getStatusString()));
        visitorDAO.update(visitor);
        visitorDTO.setStatus(RequestStatusQualifier.valueOf(visitorDTO.getStatusString()).ordinal());
        //visitorDTO.setStatusString(visitorDTO.getStatusString());
        return visitorDTO;
    }
    
}
