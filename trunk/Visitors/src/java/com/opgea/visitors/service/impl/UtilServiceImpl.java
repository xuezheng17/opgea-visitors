/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.impl;

import com.opgea.visitors.dao.CityDAO;
import com.opgea.visitors.dao.CountryDAO;
import com.opgea.visitors.domain.entities.City;
import com.opgea.visitors.domain.entities.Country;
import com.opgea.visitors.domain.modal.ExtJSTreeModel;
import com.opgea.visitors.domain.modal.SimpleModel;
import com.opgea.visitors.domain.qualifier.EmployeeType;
import com.opgea.visitors.service.UtilService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class UtilServiceImpl implements UtilService{

    
    @Autowired
    private CountryDAO countryDAO;
    @Autowired
    private CityDAO cityDAO;

    
    
    @Override
    public List<SimpleModel> getAllCountries() {
        List<Country> countries = countryDAO.findAll();
        List<SimpleModel> countryList = new ArrayList<SimpleModel>();
        for(Country country : countries){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(country.getId()));
            model.setValue(country.getName());
            countryList.add(model);
        }
        return countryList;
    }

    @Override
    public List<SimpleModel> getAllCities() {
        List<City> cities = cityDAO.findAll();
        List<SimpleModel> cityList = new ArrayList<SimpleModel>();
        for(City city : cities){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(city.getId()));
            model.setValue(city.getName());
            cityList.add(model);
        }
        return cityList;
    }

    @Override
    public List<SimpleModel> getCitiesByCountry(Long countryId) {
        List<City> cities = cityDAO.findAllByCountry(countryId);
        List<SimpleModel> cityList = new ArrayList<SimpleModel>();
        for(City city : cities){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(city.getId()));
            model.setValue(city.getName());
            cityList.add(model);
        }
        return cityList;
    }

    

    @Override
    public List<SimpleModel> getAllEmployeeTypes() {
        List<SimpleModel> employeeTypeList = new ArrayList<SimpleModel>();
        for(EmployeeType qualifier : EmployeeType.values()){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualifier.ordinal()));
            model.setValue(qualifier.name());
            employeeTypeList.add(model);
        }
        return employeeTypeList;
    }

    @Override
    public ExtJSTreeModel getMenuTree() {
        ExtJSTreeModel daddy = new ExtJSTreeModel();
        daddy.setId(1L);
        daddy.setText("Daddy");
        daddy.setDescription("Father");
        daddy.setLeaf(false);
        daddy.setExpanded("false");
        daddy.setIconCls("");
        
        ExtJSTreeModel child1 = new ExtJSTreeModel();
        child1.setId(2L);
        child1.setText("Bobby");
        child1.setDescription("First Child");
        child1.setLeaf(false);
        child1.setExpanded("false");
        child1.setIconCls("");
        
        ExtJSTreeModel child2 = new ExtJSTreeModel();
        child2.setId(3L);
        child2.setText("Rinku");
        child2.setDescription("Second Child");
        child2.setLeaf(false);
        child2.setExpanded("false");
        child2.setIconCls("");
        
        List<ExtJSTreeModel> childrenList1 = new ArrayList<ExtJSTreeModel>();
        childrenList1.add(child1);
        childrenList1.add(child2);
        
        daddy.setChildren(childrenList1);
        return daddy;
    }

        
}
