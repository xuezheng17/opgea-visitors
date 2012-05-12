/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao;

import com.opgea.visitors.domain.entities.Visitor;
import java.util.List;

/**
 *
 * @author Ramesh
 */

public interface VisitorDAO {

    public Visitor create(Visitor visitor);
    public Visitor update(Visitor visitor);
    public Visitor remove(Long id);
    public Visitor find(Long id);
    public List<Visitor> findAll();
    public List<Visitor> findAllByEmployeeId(Long employeeId);
    public List<Visitor> findAllByCompanyId(Long companyId);
    
}
