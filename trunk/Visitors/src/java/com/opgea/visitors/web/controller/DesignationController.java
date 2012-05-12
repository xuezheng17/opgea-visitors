/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.constraints.SessionConstraints;
import com.opgea.visitors.domain.modal.JsonModelMap;
import com.opgea.visitors.domain.modal.SessionData;
import com.opgea.visitors.service.DesignationService;
import com.opgea.visitors.web.dto.DesignationDTO;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
@RequestMapping(value="designation")
public class DesignationController {
    
    @Autowired
    private DesignationService designationService;
    
    
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(DesignationDTO designationDTO){
        designationDTO.setDepartmentId(designationDTO.getDepartmentId());
        designationService.create(designationDTO); 
        return JsonModelMap.success().data(designationDTO.getName());
    }
    
    @RequestMapping(method= RequestMethod.GET, value="designationList")
    public @ResponseBody Map<String, Object> getDesginationList(DesignationDTO designationDTO){
        List<DesignationDTO> designationList =
                designationService.findAllByDepartmentId(designationDTO.getDepartmentId());
        return JsonModelMap.success().data(designationList);
    }
}
