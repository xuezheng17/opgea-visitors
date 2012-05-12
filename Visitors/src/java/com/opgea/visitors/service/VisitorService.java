/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service;

import com.opgea.visitors.web.dto.VisitorDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface VisitorService {
    
    public VisitorDTO create(VisitorDTO visitorDTO);
    public VisitorDTO update(VisitorDTO visitorDTO);
    public VisitorDTO remove(Long id);
    public VisitorDTO find(Long id);
    public List<VisitorDTO> findAll();
    public List<VisitorDTO> findAllByEmployeeId(Long employeeId);
    public List<VisitorDTO> findAllByCompanyId(Long companyId);
    
    public VisitorDTO checkInVisitor(VisitorDTO visitorDTO);
    public VisitorDTO checkOutVisitor(VisitorDTO visitorDTO);
    public VisitorDTO updateVisitorStatus(VisitorDTO visitorDTO);
}
