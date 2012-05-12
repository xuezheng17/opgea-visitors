/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao;

import com.opgea.visitors.domain.entities.Department;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface DepartmentDAO {
    
    public Department create(Department department);
    public Department update(Department department);
    public Department remove(Long id);
    public Department find(Long id);
    public List<Department> findAll();
    public List<Department> findAllByCompanyId(Long companyId);
}
