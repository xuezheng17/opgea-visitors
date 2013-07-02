/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.visitors.domain.model.JsonModelMap;
import com.opgea.visitors.domain.qualifier.RequestStatusQualifier;
import com.opgea.visitors.service.VisitorService;
import com.opgea.visitors.web.dto.VisitorDTO;
import java.util.Map;
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
@RequestMapping("/notify")
public class NotificationController {
    
    @Autowired
    private VisitorService visitorService;
    
    @RequestMapping(method= RequestMethod.GET)
    public String show(){
        return "phoneNotification";
    }
    
    @RequestMapping(value="details", method= RequestMethod.POST)
    public @ResponseBody
            Map<String, Object> showDetails(@RequestParam(value="visitorId", required=true)Long visitorId){
        VisitorDTO visitorDTO = visitorService.find(visitorId);
        return JsonModelMap.success().data(visitorDTO);
    }
    
    @RequestMapping(method= RequestMethod.POST, value="reply")
    public @ResponseBody Map<String, Object> create(
                                    @RequestParam(value="visitorId", required=true)Long visitorId,
                                    @RequestParam(value="statusString", required=true)String statusString){
        
        VisitorDTO visitorDTO = visitorService.find(visitorId);
        visitorDTO.setStatus(RequestStatusQualifier.valueOf(statusString).ordinal());
        visitorDTO.setStatusString(RequestStatusQualifier.valueOf(statusString).name());
        System.out.println("Notification Controller: "+visitorDTO);
        visitorService.create(visitorDTO);

        return JsonModelMap.success().data("success");
    }
    
}
