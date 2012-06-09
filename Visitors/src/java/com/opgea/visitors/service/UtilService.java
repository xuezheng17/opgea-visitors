/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service;

import com.opgea.visitors.domain.model.ExtJSTreeModel;
import com.opgea.visitors.domain.model.SimpleModel;
import com.opgea.visitors.domain.qualifier.EmployeeType;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface UtilService {
    
    public List<SimpleModel> getAllCountries();
    public List<SimpleModel> getAllCities();
    public List<SimpleModel> getCitiesByCountry(Long countryId);
    public List<SimpleModel> getAllEmployeeTypes();
    public ExtJSTreeModel getMenuTree(EmployeeType employeeType);
}
