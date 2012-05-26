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
import com.opgea.visitors.domain.modal.CompanyStatus;
import com.opgea.visitors.domain.modal.MailModel;
import com.opgea.visitors.domain.qualifier.EmployeeType;
import com.opgea.visitors.service.ApplicationService;
import com.opgea.visitors.service.CompanyService;
import com.opgea.visitors.service.mail.MailService;
import com.opgea.visitors.web.dto.CompanyDTO;
import java.util.ArrayList;
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
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private MailService mailService;
    
    @Override
    public CompanyDTO create(CompanyDTO companyDTO) {
        
        Company company = new Company();
        company.setCompanyName(companyDTO.getName());
        company.setEmail(companyDTO.getEmail());
        company.setWebsite(companyDTO.getWebsite());
        company.setContactNo(companyDTO.getContactNo());
        companyDAO.create(company);
        CompanyStatus companyStatus = new CompanyStatus();
        companyStatus.setCompanyId(company.getId());
        companyStatus.setName(company.getCompanyName());
        applicationService.addCompanyStatus(companyStatus);
        
        Employee employee = new Employee();
        employee.setFirstName(companyDTO.getFirstName());
        employee.setMiddleInitial(companyDTO.getMiddleInitial());
        employee.setLastName(companyDTO.getLastName());
        employee.setEmail(companyDTO.getEmail());
        employee.setContactNo(companyDTO.getContactNo());
        employee.setCompany(company);
        employee.setEmployeeType(EmployeeType.ADMIN);
        employeeDAO.create(employee);
        
        Login login = new Login();
        login.setLoginId(companyDTO.getEmail());
        login.setPassword(String.valueOf(System.currentTimeMillis()));
        login.setEmployee(employee);
        login.setIsActive(true);
        loginDAO.create(login);
        
        /*
         * Mailing after confirming that company is registered
         * in OPGEA database.
         */
        
        String[] filePath = {
                              "C:\\attachments\\visitors\\welcome\\opgea.jpg"
                            }; 
        //"C:\\attachments\\Guideline.docx"

        
        MailModel mailModel = new MailModel();
        mailModel.setTo(companyDTO.getEmail());
        mailModel.setFrom("shekharkumargupta@gmail.com");
        mailModel.setSubject("OPGEA Systems");
        mailModel.setBodyText("<html><b>Congratulations!</b> <br><br>" +
                             "Your Login Id has been created for <b>OPGEA Visitors</b> Application as <br>" +
                             "<b>User Id : </b>"+login.getLoginId()+"<br> " +
                             "<b>Password : </b>"+login.getPassword()+"<br> " +
                             "Click on link to Login: <a href='http://localhost:8080/Visitors/app/login'>Login</login>" +
                             "</html>");
        mailModel.setFilePath(filePath);

        mailService.sendMimeMail(mailModel);
        
        /*
         * Adding companies' Current Token Context to the TokenContextLoaderListener 
         */

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
        Company company = (Company) companyDAO.find(id);
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyId(company.getId());
        companyDTO.setName(company.getCompanyName());
        companyDTO.setContactNo(company.getContactNo());
        companyDTO.setEmail(company.getEmail());
        companyDTO.setWebsite(company.getWebsite());
        companyDTO.setIsActive(company.getIsActive());
        return companyDTO;
    }

    @Override
    public List<CompanyDTO> findAll() {
        List<Company> companies = companyDAO.findAll();
        List<CompanyDTO> companyList = new ArrayList<CompanyDTO>();
        for(Company company: companies){
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setCompanyId(company.getId());
            companyDTO.setName(company.getCompanyName());
            companyDTO.setContactNo(company.getContactNo());
            companyDTO.setEmail(company.getEmail());
            companyDTO.setWebsite(company.getWebsite());
            companyDTO.setIsActive(company.getIsActive());
            companyList.add(companyDTO);
        }
        return companyList;
    }
    
    
}
