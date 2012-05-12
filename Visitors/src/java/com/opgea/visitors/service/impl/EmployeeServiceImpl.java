/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.impl;

import com.opgea.visitors.dao.CompanyDAO;
import com.opgea.visitors.dao.DepartmentDAO;
import com.opgea.visitors.dao.DesignationDAO;
import com.opgea.visitors.dao.EmployeeDAO;
import com.opgea.visitors.dao.LoginDAO;
import com.opgea.visitors.domain.entities.Company;
import com.opgea.visitors.domain.entities.Department;
import com.opgea.visitors.domain.entities.Designation;
import com.opgea.visitors.domain.entities.Employee;
import com.opgea.visitors.domain.entities.Login;
import com.opgea.visitors.service.EmployeeService;
import com.opgea.visitors.web.dto.EmployeeDTO;
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
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private DesignationDAO designationDAO;
    @Autowired
    private DepartmentDAO departmentDAO;
    @Autowired
    private LoginDAO loginDAO;
    
    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee employee = null;
        Company company = companyDAO.find(employeeDTO.getCompanyId());
        Designation designation = designationDAO.find(employeeDTO.getDesignationId());
        Department department = departmentDAO.find(employeeDTO.getDepartmentId());
        if(employeeDTO.getId() > 0){
            employee = employeeDAO.find(employeeDTO.getId());
        }else{
            employee = new Employee();
        }
        
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleInitial(employeeDTO.getMiddleInitial());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setContactNo(employeeDTO.getPhone1());
        employee.setCompany(company);
        employee.setDesignation(designation);
        employee.setDepartment(department);
        if(employeeDTO.getPicture()!= null && employeeDTO.getPicture().length > 0){
            employee.setPicture(employeeDTO.getPicture());
        }

        if(employeeDTO.getId() > 0){
            employeeDAO.update(employee);
        }else{
            employeeDAO.create(employee);
            Login login = new Login();
            login.setLoginId(employeeDTO.getEmail());
            login.setPassword(String.valueOf(Calendar.getInstance().getTimeInMillis()));
            login.setIsActive(Boolean.TRUE);
            login.setEmployee(employee);
            loginDAO.create(login);
        }

        return employeeDTO;

    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EmployeeDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EmployeeDTO find(Long id) {
        Employee employee = employeeDAO.find(id);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setMiddleInitial(employee.getMiddleInitial());
        employeeDTO.setLastName(employee.getLastName());
        if(employee.getDesignation() != null){
            employeeDTO.setDesignationId(employee.getDesignation().getId());
            employeeDTO.setDesignationName(employee.getDesignation().getName());
        }
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhone1(employee.getContactNo());
        employeeDTO.setPicture(employee.getPicture());
        
        employeeDTO.setCompanyId(employee.getCompany().getId());
        if(employee.getDepartment() != null){
            employeeDTO.setDepartmentId(employee.getDepartment().getId());
            employeeDTO.setDepartmentName(employee.getDepartment().getName());
        }
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeDAO.findAll();
        List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
        for(Employee employee : employees){
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setMiddleInitial(employee.getMiddleInitial());
            employeeDTO.setLastName(employee.getLastName());
            if(employee.getDesignation() != null){
                employeeDTO.setDesignationId(employee.getDesignation().getId());
                employeeDTO.setDesignationName(employee.getDesignation().getName());
            }
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setPhone1(employee.getContactNo());
            employeeDTO.setPicture(employee.getPicture());
            
            employeeDTO.setCompanyId(employee.getCompany().getId());
            if(employee.getDepartment() != null){
                employeeDTO.setDepartmentId(employee.getDepartment().getId());
                employeeDTO.setDepartmentName(employee.getDepartment().getName());
            }
            employeeList.add(employeeDTO);
        }
        return employeeList;
    }

    @Override
    public List<EmployeeDTO> findAllByCompanyId(Long companyId) {
        List<Employee> employees = employeeDAO.findAllByCompanyId(companyId);
        List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
        for(Employee employee : employees){
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setMiddleInitial(employee.getMiddleInitial());
            employeeDTO.setLastName(employee.getLastName());
            if(employee.getDesignation() != null){
                employeeDTO.setDesignationId(employee.getDesignation().getId());
                employeeDTO.setDesignationName(employee.getDesignation().getName());
            }
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setPhone1(employee.getContactNo());
            employeeDTO.setPicture(employee.getPicture());
            
            employeeDTO.setCompanyId(employee.getCompany().getId());
            if(employee.getDepartment() != null){
                employeeDTO.setDepartmentId(employee.getDepartment().getId());
                employeeDTO.setDepartmentName(employee.getDepartment().getName());
            }
            employeeList.add(employeeDTO);
        }
        return employeeList;
    }

    @Override
    public List<EmployeeDTO> findAllByDepartmentId(Long departmentId) {
        List<Employee> employees = employeeDAO.findAllByDepartmentId(departmentId);
        List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
        for(Employee employee : employees){
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setMiddleInitial(employee.getMiddleInitial());
            employeeDTO.setLastName(employee.getLastName());
            if(employee.getDesignation() != null){
                employeeDTO.setDesignationId(employee.getDesignation().getId());
                employeeDTO.setDesignationName(employee.getDesignation().getName());
            }
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setPhone1(employee.getContactNo());
            employeeDTO.setPicture(employee.getPicture());
            
            employeeDTO.setCompanyId(employee.getCompany().getId());
            if(employee.getDepartment() != null){
                employeeDTO.setDepartmentId(employee.getDepartment().getId());
                employeeDTO.setDepartmentName(employee.getDepartment().getName());
            }
            employeeList.add(employeeDTO);
        }
        return employeeList;
    }
    
}
