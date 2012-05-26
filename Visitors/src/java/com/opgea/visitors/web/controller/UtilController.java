/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.constraints.SessionConstraints;
import com.opgea.visitors.domain.modal.ExtJSTreeModel;
import com.opgea.visitors.domain.modal.JsonModelMap;
import com.opgea.visitors.domain.modal.SessionData;
import com.opgea.visitors.domain.modal.SimpleModel;
import com.opgea.visitors.domain.qualifier.EmployeeType;
import com.opgea.visitors.service.EmployeeService;
import com.opgea.visitors.service.UtilService;
import com.opgea.visitors.service.VisitorService;
import com.opgea.visitors.web.dto.EmployeeDTO;
import com.opgea.visitors.web.dto.VisitorDTO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="util")
public class UtilController{
    
    
    @Autowired
    private UtilService utilService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private VisitorService visitorService;

    
    @RequestMapping(method= RequestMethod.GET, value="countryList")
    public @ResponseBody Map<String, Object> getCountryList(){
        List<SimpleModel> countryList = utilService.getAllCountries();
        return JsonModelMap.success().data(countryList);
    }
    
    
    @RequestMapping(method= RequestMethod.GET, value="cityList")
    public @ResponseBody Map<String, Object> getCityList(@RequestParam(required=false) String countryId, HttpServletRequest request){
        List<SimpleModel> cityList = utilService.getCitiesByCountry(new Long(countryId));
        return JsonModelMap.success().data(cityList);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="employeeTypeList")
    public @ResponseBody Map<String, Object> getEmployeeTypeList(@RequestParam(required=false) String countryId, HttpServletRequest request){
        List<SimpleModel> employeeTypeList = utilService.getAllEmployeeTypes();
        return JsonModelMap.success().data(employeeTypeList);
    }

    @RequestMapping(method= RequestMethod.GET, value="menuTree")
    public @ResponseBody Map<String, Object> getMenuTree(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        EmployeeType employeeType = EmployeeType.values()[sessionData.getEmployeeType()];
        
        ExtJSTreeModel treeModel = new ExtJSTreeModel();
        treeModel.setText("ROOT");
        treeModel.setLeaf(false);
        
        ExtJSTreeModel menuNode = utilService.getMenuTree(employeeType);
        List<ExtJSTreeModel> menuList = new ArrayList<ExtJSTreeModel>();
        menuList.add(menuNode);
        return JsonModelMap.success().add("children", menuList);
    }
    
    @RequestMapping(value="employeeImage/{employeeId}", method=RequestMethod.GET)
    public void getEmployeePicture(@PathVariable Long employeeId,   HttpServletResponse response){
        
        EmployeeDTO employeeDTO = employeeService.find(employeeId);
        byte[] thumb = employeeDTO.getPicture();
 
        String name = "EmployeeImage";
        response.setContentType("image/jpeg");
        response.setContentLength(thumb.length);
 
        response.setHeader("Content-Disposition", "inline; filename=\"" + name
                + "\"");
 
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
 
        try {
            input = new BufferedInputStream(new ByteArrayInputStream(thumb));
            output = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[8192];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } catch (IOException e) {
            System.out.println("There are errors in reading/writing image stream "
                    + e.getMessage());
        } finally {
            if (output != null)
                try {
                    output.close();
                } catch (IOException ignore) {
                }
            if (input != null)
                try {
                    input.close();
                } catch (IOException ignore) {
                }
        }
    }
    
    @RequestMapping(value="visitorImage/{visitorId}", method=RequestMethod.GET)
    public void getVisitorPicture(@PathVariable Long visitorId,   HttpServletResponse response){
        
        VisitorDTO visitorDTO = visitorService.find(visitorId);
        System.out.println("VisitorName: "+visitorDTO.getName());
        byte[] thumb = visitorDTO.getPicture();
        System.out.println("Visitor Image: "+thumb);
        String name = "VisitorImage";
        response.setContentType("image/jpeg");
        response.setContentLength(thumb.length);
 
        response.setHeader("Content-Disposition", "inline; filename=\"" + name
                + "\"");
 
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
 
        try {
            input = new BufferedInputStream(new ByteArrayInputStream(thumb));
            output = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[8192];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } catch (IOException e) {
            System.out.println("There are errors in reading/writing image stream "
                    + e.getMessage());
        } finally {
            if (output != null)
                try {
                    output.close();
                } catch (IOException ignore) {
                }
            if (input != null)
                try {
                    input.close();
                } catch (IOException ignore) {
                }
        }
    }

}
