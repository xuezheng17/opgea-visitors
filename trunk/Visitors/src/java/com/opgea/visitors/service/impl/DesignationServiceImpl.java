/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.impl;

import com.opgea.visitors.dao.CompanyDAO;
import com.opgea.visitors.dao.DepartmentDAO;
import com.opgea.visitors.dao.DesignationDAO;
import com.opgea.visitors.domain.entities.Department;
import com.opgea.visitors.domain.entities.Designation;
import com.opgea.visitors.service.DesignationService;
import com.opgea.visitors.web.dto.DesignationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class DesignationServiceImpl implements DesignationService{

    @Autowired
    private DesignationDAO designationDAO;
    
    @Autowired
    private DepartmentDAO departmentDAO;
    
    @Override
    public DesignationDTO create(DesignationDTO designationDTO) {
        Designation designation = new Designation();
        if(designationDTO.getId() > 0){
            designation = designationDAO.find(designationDTO.getId());
        }
        
        designation.setName(designationDTO.getName());
        
        if(designationDTO.getId() > 0){
            designationDAO.update(designation);
        }else{
            Department department = departmentDAO.find(designationDTO.getDepartmentId());
            designation.setDepartment(department);
            designationDAO.create(designation);
        }
        
        return designationDTO;
    }

    @Override
    public DesignationDTO update(DesignationDTO designationDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DesignationDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DesignationDTO find(Long id) {
        Designation designation = designationDAO.find(id);
        DesignationDTO designationDTO = new DesignationDTO();
        designationDTO.setId(designation.getId());
        designationDTO.setName(designation.getName());
        //designationDTO.setCompanyId(designation.getCompany().getId());
        return designationDTO;
    }

    @Override
    public List<DesignationDTO> findAll() {
        List<Designation> designations = designationDAO.findAll();
        List<DesignationDTO> designationList = new ArrayList<DesignationDTO>();
        for(Designation designation : designations){
            DesignationDTO designationDTO = new DesignationDTO();
            designationDTO.setId(designation.getId());
            designationDTO.setName(designation.getName());
            //designationDTO.setCompanyId(designation.getCompany().getId());
            designationList.add(designationDTO);
        }
        return designationList;
    }

    @Override
    public List<DesignationDTO> findAllByDepartmentId(Long departmentId) {
        List<Designation> designations = designationDAO.findAllByDepartmentId(departmentId);
        List<DesignationDTO> designationList = new ArrayList<DesignationDTO>();
        for(Designation designation : designations){
            DesignationDTO designationDTO = new DesignationDTO();
            designationDTO.setId(designation.getId());
            designationDTO.setName(designation.getName());
            //designationDTO.setCompanyId(designation.getCompany().getId());
            designationList.add(designationDTO);
        }
        return designationList;
    }
    
}
