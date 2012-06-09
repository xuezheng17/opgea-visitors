/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.constraints.ActionConstraints;
import com.opgea.constraints.SessionConstraints;
import com.opgea.visitors.domain.model.EmployeeStatus;
import com.opgea.visitors.domain.model.JsonModelMap;
import com.opgea.visitors.domain.model.SessionData;
import com.opgea.visitors.domain.qualifier.OnlineQualifier;
import com.opgea.visitors.service.ApplicationService;
import com.opgea.visitors.service.EmployeeService;
import com.opgea.visitors.service.LoginService;
import com.opgea.visitors.web.dto.ChangePasswordDTO;
import com.opgea.visitors.web.dto.EmployeeDTO;
import com.opgea.visitors.web.dto.LoginDTO;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="/login")
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private ApplicationService applicationService;
    
    @RequestMapping(method= RequestMethod.GET)
    public String show(HttpServletRequest request){
        HttpSession session = request.getSession();
        String targetPage = "login";
        if(session.getAttribute(SessionConstraints.SESSION_DATA.name()) != null){
            targetPage = "redirect: home";
        }
        return targetPage;
    }
    
    @RequestMapping(method= RequestMethod.POST, value="verify")
    public @ResponseBody Map<String, Object> verify(LoginDTO loginDTO, HttpServletRequest request){
        String success = ActionConstraints.FAILURE;
        Boolean authentic = loginService.isAuthenticUser(loginDTO.getLoginId(), loginDTO.getPassword());

        if(authentic){
            success = ActionConstraints.SUCCESS;
            Long employeeId = loginService.find(loginDTO.getLoginId()).getEmployeeId();
            EmployeeDTO employeeDTO = employeeService.find(employeeId);
            HttpSession session  = request.getSession();
            SessionData sessionData = new SessionData();
            sessionData.setLoginId(loginDTO.getLoginId());
            sessionData.setEmpId(employeeDTO.getId());
            sessionData.setEmployeeType(employeeDTO.getEmployeeType());
            sessionData.setEmployeeTypeName(employeeDTO.getEmployeeTypeName());
            sessionData.setEmpName(employeeDTO.getFirstName()+" "+employeeDTO.getMiddleInitial()+" "+employeeDTO.getLastName().trim());
            sessionData.setBranchId(employeeDTO.getBranchId());
            sessionData.setCompanyId(employeeDTO.getCompanyId());
            sessionData.setDepartmentId(employeeDTO.getDepartmentId());
            
            session.setAttribute(SessionConstraints.SESSION_DATA.toString(), sessionData);
        }
        return JsonModelMap.success().data(success);
    }
    
    @RequestMapping(value="loginInfo", method= RequestMethod.GET)
    public @ResponseBody 
    Map<String, Object> getLoginInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        
        return JsonModelMap.success().data(sessionData);
    }
    
    @RequestMapping(value="updatePassword", method= RequestMethod.POST)
    public @ResponseBody Map<String, Object> updatePassword(ChangePasswordDTO passwordDTO){
        System.out.println("ChangePasswordDTO: "+passwordDTO);
        Boolean status = loginService.updatePassword(passwordDTO);
        if(status == false){
            return JsonModelMap.success().data("Password could not updated.");
        }else{
            return JsonModelMap.success().data("Password changed.");
        }
    }
    
    @RequestMapping(value="logout", method= RequestMethod.GET)
    public @ResponseBody 
    Map<String, Object> logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        EmployeeStatus employeeStatus = new EmployeeStatus();
        employeeStatus.setCompanyId(sessionData.getCompanyId());
        employeeStatus.setEmployeeId(sessionData.getEmpId());
        employeeStatus.setOnlineStatus(OnlineQualifier.OFFLINE);
        applicationService.removeEmployeeStatus(employeeStatus);
        session.invalidate();
        return JsonModelMap.success().data("login");
    }
    
    @RequestMapping(value="isExistingUser", method= RequestMethod.GET)
    public @ResponseBody 
    Map<String, Object> isExistingUser(@RequestParam(value="emailId", required=false)String emailId, HttpServletRequest request){
        
        if(loginService.find(emailId) != null){
            return JsonModelMap.success().data("YES");
        }else{
            return JsonModelMap.success().data("NO");
        }
    }
}
