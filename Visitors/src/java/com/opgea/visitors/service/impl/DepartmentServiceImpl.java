/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.impl;

import com.opgea.visitors.dao.CompanyDAO;
import com.opgea.visitors.dao.DepartmentDAO;
import com.opgea.visitors.domain.entities.Company;
import com.opgea.visitors.domain.entities.Department;
import com.opgea.visitors.service.DepartmentService;
import com.opgea.visitors.web.dto.DepartmentDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

    
    @Autowired
    private DepartmentDAO departmentDAO;
    
    @Autowired
    private CompanyDAO companyDAO;
    
    @Override
    public DepartmentDTO create(DepartmentDTO departmentDTO) {
        Department department = new Department();
        if(departmentDTO.getId() > 0){
            department = departmentDAO.find(departmentDTO.getId());
        }
        
        department.setName(departmentDTO.getName());
        
        if(departmentDTO.getId() > 0){
            departmentDAO.update(department);
        }else{
            Company company = companyDAO.find(departmentDTO.getCompanyId());
            department.setCompany(company);
            departmentDAO.create(department);
        }
        
        return departmentDTO;
    }

    @Override
    public DepartmentDTO update(DepartmentDTO departmentDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DepartmentDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DepartmentDTO find(Long id) {
        Department department = departmentDAO.find(id);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        //designationDTO.setCompanyId(designation.getCompany().getId());
        return departmentDTO;
    }

    @Override
    public List<DepartmentDTO> findAll() {
        List<Department> departments = departmentDAO.findAll();
        List<DepartmentDTO> departmentList = new ArrayList<DepartmentDTO>();
        for(Department department: departments){
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setName(department.getName());
            //designationDTO.setCompanyId(designation.getCompany().getId());
            departmentList.add(departmentDTO);
        }
        return departmentList;
    }

    @Override
    public List<DepartmentDTO> findAllByCompanyId(Long companyId) {
        List<Department> departments = departmentDAO.findAllByCompanyId(companyId);
        List<DepartmentDTO> departmentList = new ArrayList<DepartmentDTO>();
        for(Department department: departments){
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setName(department.getName());
            //designationDTO.setCompanyId(designation.getCompany().getId());
            departmentList.add(departmentDTO);
        }
        return departmentList;
    }

    
}
