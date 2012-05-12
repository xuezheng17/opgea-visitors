/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao;

import com.opgea.visitors.domain.entities.Designation;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface DesignationDAO {
    
    public Designation create(Designation designation);
    public Designation update(Designation designation);
    public Designation remove(Long id);
    public Designation find(Long id);
    public List<Designation> findAll();
    public List<Designation> findAllByDepartmentId(Long departmentId);
}
