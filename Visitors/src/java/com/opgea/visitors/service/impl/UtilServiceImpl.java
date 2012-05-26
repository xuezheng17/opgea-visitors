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
import java.util.Locale;
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
    public ExtJSTreeModel getMenuTree(EmployeeType employeeType) {
        ExtJSTreeModel rootMenu = new ExtJSTreeModel();
        rootMenu.setId(0L);
        rootMenu.setText(employeeType.name().toUpperCase(Locale.US));
        rootMenu.setDescription("Setting Basics of Application");
        rootMenu.setLeaf(false);
        rootMenu.setExpanded("false");
        rootMenu.setIconCls("settingIcon");

        /*
         * For ADMIN and RECEPTION
         */
        ExtJSTreeModel searchVisitor = new ExtJSTreeModel(1L, "Search Visitors", "userIcon", "Search Visitors", Boolean.TRUE, "FALSE", null);
        
        /*
         * Items Authorized to ADMIN
         */
        ExtJSTreeModel department = new ExtJSTreeModel(2L, "Department", "monitorIcon", "Create Department", Boolean.TRUE, "FALSE", null);
        ExtJSTreeModel designation = new ExtJSTreeModel(3L, "Designation", "bookIcon", "Create Designation", Boolean.TRUE, "FALSE", null);
        ExtJSTreeModel employee = new ExtJSTreeModel(4L, "Employee", "userIcon", "Create Employee", Boolean.TRUE, "FALSE", null);
        
        /*
         * Items Authorized to RECEPTION
         */
        ExtJSTreeModel entry = new ExtJSTreeModel(5L, "Entry", "timeInIcon", "Entry Point", Boolean.TRUE, "FALSE", null);
        
        
        /*
         * Items Authorized to EMPLOYEE
         */
        ExtJSTreeModel requestList = new ExtJSTreeModel(9L, "Request List", "userIcon", "Request List", Boolean.TRUE, "FALSE", null);
        

        
        List<ExtJSTreeModel> menuList = new ArrayList<ExtJSTreeModel>();
        if(employeeType == EmployeeType.ADMIN){
            menuList.add(department);
            menuList.add(designation);
            menuList.add(employee);
        }
        if(employeeType == EmployeeType.RECEPTION){
            menuList.add(entry);
        }
        if(employeeType == EmployeeType.EMPLOYEE){
            menuList.add(requestList);
        }
        menuList.add(searchVisitor);
        
        rootMenu.setChildren(menuList);
        return rootMenu;
    }

        
}
