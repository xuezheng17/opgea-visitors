/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.constraints.SessionConstraints;
import com.opgea.util.ImageUtil;
import com.opgea.visitors.domain.modal.JsonModelMap;
import com.opgea.visitors.domain.modal.SessionData;
import com.opgea.visitors.domain.modal.VisitorStatus;
import com.opgea.visitors.domain.modal.VisitorStatus;
import com.opgea.visitors.domain.qualifier.EmployeeType;
import com.opgea.visitors.domain.qualifier.RequestStatusQualifier;
import com.opgea.visitors.service.ApplicationService;
import com.opgea.visitors.service.VisitorService;
import com.opgea.visitors.web.dto.VisitorDTO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
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
@RequestMapping("visitors")
public class VisitorController {
    
    @Autowired
    private VisitorService visitorService;
    
    @Autowired
    private ApplicationService applicationService;
    
    
    @RequestMapping(method= RequestMethod.POST, value="createImage")
    public @ResponseBody Map<String, Object> createImage(VisitorDTO visitorDTO, HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        System.out.println("Create Image");

        ImageUtil imageUtil = new ImageUtil();
        imageUtil.createImage(request.getInputStream(), "c:/visitor_images/"+sessionData.getEmpId()+".jpeg");
        System.out.println("Done");
        return JsonModelMap.success();
    }
    
    
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(VisitorDTO visitorDTO, HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());


        
        
        BufferedImage originalImage = null;
        if(sessionData.getEmployeeType() == EmployeeType.RECEPTION.ordinal() || sessionData.getEmployeeType() == EmployeeType.ADMIN.ordinal()) {
            visitorDTO.setCreatedBy(sessionData.getEmpId());
            originalImage = ImageIO.read(new File("c:/visitor_images/"+sessionData.getEmpId()+".jpeg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            visitorDTO.setPicture(imageInByte);
            baos.close();
        }

        visitorDTO.setCompanyId(sessionData.getCompanyId());
        
        System.out.println("VisitorController >> "+visitorDTO);
        visitorService.create(visitorDTO);
        
        return JsonModelMap.success().data(visitorDTO.getName()+" --> "+visitorDTO.getId());
    }
    
    @RequestMapping(method=RequestMethod.GET, value="visitorList", params={}  )
    public @ResponseBody Map<String, Object> getVisitorList(@RequestParam(value="employeeId", required=false) Long employeeId, HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        //Long employeeId = Long.parseLong(request.getParameter("employeeId").toString());
        List<VisitorDTO> visitors = null;
        if(employeeId > 0){
            visitors = visitorService.findAllByEmployeeId(employeeId, sessionData.getEmpId());
        }else{
            visitors = visitorService.findAllByCompanyId(sessionData.getCompanyId(), sessionData.getEmpId());
        }
        return JsonModelMap.success().data(visitors);
    }
    
    @RequestMapping(method=RequestMethod.GET, value="searchVisitors", params={}  )
    public @ResponseBody Map<String, Object> searchVisitors(
                    @RequestParam(value="visitingDate", required=false) String visitingDate,
                    @RequestParam(value="searchKey", required=false) String searchKey, 
                    HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        //Long employeeId = Long.parseLong(request.getParameter("employeeId").toString());
        System.out.println("Search Visitors Controller: "+searchKey);
        List<VisitorDTO> visitors = null;
        if(sessionData.getEmployeeType() == EmployeeType.ADMIN.ordinal()){
            visitors = visitorService.searchVisitors(sessionData.getCompanyId(), 0L, visitingDate, searchKey);
        }
        if(sessionData.getEmployeeType() == EmployeeType.RECEPTION.ordinal()){
            visitors = visitorService.searchVisitors(sessionData.getCompanyId(), 0L, visitingDate, searchKey);
        }
        if(sessionData.getEmployeeType() == EmployeeType.EMPLOYEE.ordinal()){
            visitors = visitorService.searchVisitors(sessionData.getCompanyId(), sessionData.getEmpId(), visitingDate, searchKey);
        }
        return JsonModelMap.success().data(visitors);
    }
    
    @RequestMapping(method= RequestMethod.POST, value="updateStatus")
    public @ResponseBody Map<String, Object> updateStatus(VisitorDTO visitorDTO, HttpServletRequest request){
        
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        visitorDTO.setCompanyId(sessionData.getCompanyId());
        if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.CHECK_IN.toString())){
            //visitorService.checkInVisitor(visitorDTO);
        }
        if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.CHECK_OUT.toString())){
            //visitorService.checkOutVisitor(visitorDTO);
        }else{
            //visitorService.updateVisitorStatus(visitorDTO);
        }
        return JsonModelMap.success().data(visitorDTO.getName());
    }
    
    @RequestMapping(method= RequestMethod.GET, value="notification")
    public @ResponseBody Map<String, Object> getNotification(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        List<VisitorStatus> visitors = 
                applicationService.findAllVisitorStatusByEmployeeId(sessionData.getCompanyId(), sessionData.getEmpId());
        Map<String, Object> responseMap = new HashMap<String, Object>();
        VisitorStatus visitorStatus = 
                applicationService.findRespondedVisitorStatusByEmployeeId(sessionData.getCompanyId(), sessionData.getEmpId());
        
        /*
        for(VisitorStatus visitorState : visitors){
            if(visitorState.getForwardedTo().equals(sessionData.getEmpId())){
                visitorStatus = visitorState;
            }
        }*/
        
        int count = 0;
        if(visitors != null){
            count = visitors.size();
        }
        
        responseMap.put("success", "success");
        responseMap.put("data", visitorStatus);
        responseMap.put("count", count);

        return responseMap;
        
        //return JsonModelMap.success().data(visitors);
    }
}
