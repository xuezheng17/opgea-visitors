/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service;

import com.opgea.visitors.domain.modal.ExtJSTreeModel;
import com.opgea.visitors.domain.modal.SimpleModel;
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
    public ExtJSTreeModel getMenuTree();
}
