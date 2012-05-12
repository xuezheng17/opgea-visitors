/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.impl;

import com.opgea.visitors.dao.CompanyDAO;
import com.opgea.visitors.dao.EmployeeDAO;
import com.opgea.visitors.dao.LoginDAO;
import com.opgea.visitors.domain.entities.Company;
import com.opgea.visitors.domain.entities.Employee;
import com.opgea.visitors.domain.entities.Login;
import com.opgea.visitors.service.CompanyService;
import com.opgea.visitors.web.dto.CompanyDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class CompanyServiceImpl implements CompanyService{

   @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private LoginDAO loginDAO;
    
    @Override
    public CompanyDTO create(CompanyDTO companyDTO) {
        
        Company company = new Company();
        company.setCompanyName(companyDTO.getName());
        company.setEmail(companyDTO.getEmail());
        company.setWebsite(companyDTO.getWebsite());
        company.setContactNo(companyDTO.getContactNo());
        companyDAO.create(company);
        
        Employee employee = new Employee();
        employee.setFirstName(companyDTO.getFirstName());
        employee.setMiddleInitial(companyDTO.getMiddleInitial());
        employee.setLastName(companyDTO.getLastName());
        employee.setEmail(companyDTO.getEmail());
        employee.setContactNo(companyDTO.getContactNo());
        employee.setCompany(company);
        employeeDAO.create(employee);
        
        Login login = new Login();
        login.setLoginId(companyDTO.getEmail());
        login.setPassword(String.valueOf(System.currentTimeMillis()));
        login.setEmployee(employee);
        login.setIsActive(true);
        loginDAO.create(login);

        return companyDTO;
    }

    @Override
    public CompanyDTO update(CompanyDTO companyDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CompanyDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CompanyDTO find(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CompanyDTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
