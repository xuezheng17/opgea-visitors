/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.constraints.SessionConstraints;
import com.opgea.visitors.domain.model.JsonModelMap;
import com.opgea.visitors.domain.model.SessionData;
import com.opgea.visitors.service.DepartmentService;
import com.opgea.visitors.web.dto.DepartmentDTO;
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
@RequestMapping(value="department")
public class DepartmentController {
    
     @Autowired
    private DepartmentService departmentService;
    
    
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(DepartmentDTO departmentDTO,
                                        HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) 
                session.getAttribute(SessionConstraints.SESSION_DATA.name());
        departmentDTO.setCompanyId(sessionData.getCompanyId());
        departmentService.create(departmentDTO); 
        return JsonModelMap.success().data(departmentDTO.getName());
    }
    
    @RequestMapping(method= RequestMethod.GET, value="departmentList")
    public @ResponseBody Map<String, Object> getDesginationList(DepartmentDTO departmentDTO,
                                        HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) 
                session.getAttribute(SessionConstraints.SESSION_DATA.name());
        List<DepartmentDTO> departmentList =
                departmentService.findAllByCompanyId(sessionData.getCompanyId());
        return JsonModelMap.success().data(departmentList);
    }
}
