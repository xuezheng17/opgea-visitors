/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.constraints.SessionConstraints;
import com.opgea.util.DateUtil;
import com.opgea.visitors.domain.modal.JsonModelMap;
import com.opgea.visitors.domain.modal.SessionData;
import com.opgea.visitors.domain.qualifier.RequestStatusQualifier;
import com.opgea.visitors.service.VisitorService;
import com.opgea.visitors.web.dto.VisitorDTO;
import java.io.IOException;
import java.util.List;
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
@RequestMapping("visitors")
public class VisitorController {
    
    @Autowired
    private VisitorService visitorService;
    
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(VisitorDTO visitorDTO, HttpServletRequest request) throws IOException{
        System.out.println("VisitorController >> Create");
        
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        visitorDTO.setCompanyId(sessionData.getCompanyId());
        visitorService.create(visitorDTO);
        
        /*
         * Do not delete.
         * Image processing code;
        BufferedInputStream bis = new BufferedInputStream(request.getInputStream());
        FileOutputStream fos;

        try{
            fos = new FileOutputStream(new File("c:/image.jpeg"));
            byte[] bs = new byte[1024];
            int len;

            while ((len = bis.read(bs, 0, bs.length)) != -1) {
                fos.write(bs, 0, len);
            }
            fos.close();
        }
        catch (Exception ex)
        {}
        bis.close();
        */
        return JsonModelMap.success().data(visitorDTO.getName()+" --> "+visitorDTO.getEmployeeId());
    }
    
    @RequestMapping(method=RequestMethod.GET, value="visitorList", params={}  )
    public @ResponseBody Map<String, Object> getVisitorList(@RequestParam(value="employeeId", required=false) Long employeeId, HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        //Long employeeId = Long.parseLong(request.getParameter("employeeId").toString());
        System.out.println("VisitorController >> Employee Id: "+employeeId);
        List<VisitorDTO> visitors = null;
        if(employeeId > 0){
            System.out.append("VisitorList: 1");
            visitors = visitorService.findAllByEmployeeId(employeeId);
            
        }else{
            System.out.append("VisitorList: 2");
            visitors = visitorService.findAllByCompanyId(sessionData.getCompanyId());
        }
        return JsonModelMap.success().data(visitors);
    }
    
    @RequestMapping(method= RequestMethod.POST, value="updateStatus")
    public @ResponseBody Map<String, Object> updateStatus(VisitorDTO visitorDTO, HttpServletRequest request){
        System.out.println("VisitorController >> updateStatus");
        
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        visitorDTO.setCompanyId(sessionData.getCompanyId());
        if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.CHECK_IN.toString())){
            visitorService.checkInVisitor(visitorDTO);
        }
        if(visitorDTO.getStatusString().equalsIgnoreCase(RequestStatusQualifier.CHECK_OUT.toString())){
            visitorService.checkOutVisitor(visitorDTO);
        }else{
            visitorService.updateVisitorStatus(visitorDTO);
        }
        return JsonModelMap.success().data(visitorDTO.getName());
    }
}
