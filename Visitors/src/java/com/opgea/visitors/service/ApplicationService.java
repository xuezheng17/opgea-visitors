/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service;

import com.opgea.visitors.domain.model.CompanyStatus;
import com.opgea.visitors.domain.model.EmployeeStatus;
import com.opgea.visitors.domain.model.VisitorStatus;
import com.opgea.visitors.domain.qualifier.OnlineQualifier;
import com.opgea.visitors.web.dto.VisitorDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ramesh
 */
public interface ApplicationService {
    
    public CompanyStatus addCompanyStatus(CompanyStatus companyStatus);
    public CompanyStatus removeCompanyStatus(Long companyId);
    public CompanyStatus updateCompanyStatus(CompanyStatus companyStatus);
    public Map<Long, CompanyStatus> findAllCompanies();
    public CompanyStatus findCompany(Long id);
    
    
    public EmployeeStatus addEmployeeStatus(EmployeeStatus employeeStatus);
    public EmployeeStatus removeEmployeeStatus(EmployeeStatus employeeStatus);
    public EmployeeStatus updateEmployeeStatus(EmployeeStatus employeeStatus);
    public List<EmployeeStatus> findAllEmployeeStatus(Long companyId);
    public OnlineQualifier findEmployeeOnlineStatus(EmployeeStatus employeeStatus);
    
    public VisitorStatus addVisitorStatus(VisitorStatus visitorStatus);
    public VisitorStatus removeVisitorStatus(VisitorStatus visitorStatus);
    public VisitorStatus updateVisitorStatus(VisitorStatus visitorStatus);
    public VisitorDTO updateVisitorStatus(VisitorDTO visitorDTO);
    public List<VisitorStatus> findAllVisitorStatus(Long companyId);
    public List<VisitorStatus> findAllVisitorStatusByEmployeeId(Long companyId, Long employeeId);
    public VisitorStatus findRespondedVisitorStatusByEmployeeId(Long companyId, Long employeeId);
    public VisitorStatus findVisitorRequestStatus(VisitorStatus visitorStatus);
    public VisitorStatus findVisitorRequestStatus(Long companyId, Long visitorId);
    
}
