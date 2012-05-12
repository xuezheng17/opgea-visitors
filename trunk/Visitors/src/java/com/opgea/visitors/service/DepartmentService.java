/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service;

import com.opgea.visitors.web.dto.DepartmentDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface DepartmentService {
    
    public DepartmentDTO create(DepartmentDTO departmentDTO);
    public DepartmentDTO update(DepartmentDTO departmentDTO);
    public DepartmentDTO remove(Long id);
    public DepartmentDTO find(Long id);
    public List<DepartmentDTO> findAll();
    public List<DepartmentDTO> findAllByCompanyId(Long companyId);
}
