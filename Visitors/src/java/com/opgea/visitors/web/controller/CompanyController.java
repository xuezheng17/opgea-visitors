/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.visitors.domain.model.JsonModelMap;
import com.opgea.visitors.service.CompanyService;
import com.opgea.visitors.service.LoginService;
import com.opgea.visitors.web.dto.CompanyDTO;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="/company")
public class CompanyController {
    
    
    @Autowired
    private CompanyService companyService;
    @Autowired
    private LoginService loginService;
    
    @RequestMapping(method= RequestMethod.GET)
    private String show(){
        return "company";
    }
    
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(CompanyDTO companyDTO){
        if(loginService.find(companyDTO.getEmail()) == null){
            companyService.create(companyDTO); 
            return JsonModelMap.success().data("Login Id <b>"+companyDTO.getEmail()+"</b> has created successfully!"
                                                + "<br>Please check your email account for password.");
        }else{
            return JsonModelMap.success().data("<b>"+companyDTO.getEmail()+"</b> is already register.<br>Contact us if you forgot your password.");
        }
    }
}
