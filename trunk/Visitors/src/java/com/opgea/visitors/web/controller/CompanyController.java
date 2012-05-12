/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.visitors.domain.modal.JsonModelMap;
import com.opgea.visitors.service.CompanyService;
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
    
    @RequestMapping(method= RequestMethod.GET)
    private String show(){
        return "company";
    }
    
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(CompanyDTO companyDTO){
        companyService.create(companyDTO); 
        System.out.println("Cms >> PostCreate:");
        return JsonModelMap.success().data(companyDTO.getEmail());
    }
}
