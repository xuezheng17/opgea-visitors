/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao;

import com.opgea.visitors.domain.entities.Employee;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface EmployeeDAO {
    
    public Employee create(Employee employee);
    public Employee update(Employee employee);
    public Employee remove(Long id);
    public Employee find(Long id);
    public List<Employee> searchEmployees(Long companyId, String searchKey) ;
    public List<Employee> findAll();
    public List<Employee> findAllByCompanyId(Long companyId);
    public List<Employee> findAllByDepartmentId(Long departmentId);
}
