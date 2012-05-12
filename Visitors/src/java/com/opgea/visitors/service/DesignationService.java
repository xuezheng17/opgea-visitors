/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service;

import com.opgea.visitors.web.dto.DesignationDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface DesignationService {
    
    public DesignationDTO create(DesignationDTO designationDTO);
    public DesignationDTO update(DesignationDTO designationDTO);
    public DesignationDTO remove(Long id);
    public DesignationDTO find(Long id);
    public List<DesignationDTO> findAll();
    public List<DesignationDTO> findAllByDepartmentId(Long departmentId);
}
