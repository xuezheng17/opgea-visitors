/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.constraints.SessionConstraints;
import com.opgea.visitors.domain.modal.ExtJSFormResult;
import com.opgea.visitors.domain.modal.FileUploadBean;
import com.opgea.visitors.domain.modal.JsonModelMap;
import com.opgea.visitors.domain.modal.SessionData;
import com.opgea.visitors.service.EmployeeService;
import com.opgea.visitors.web.dto.EmployeeDTO;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="/user")
public class EmployeeController {
    
    private static final String FILE_UPLOAD_SUCCESS_RESPONSE = 
		"{\"success\":%s,\"message\":\"%s\",\"fileData\":{\"size\":%s," +
		" \"name\":\"%s\", \"type\":\"%s\"}}";
    
    @Autowired
    private EmployeeService employeeService;
    
    
    protected String buildSuccessFileUploadResonse(String message, long fileSize, String fileName, String fileType){
		return String.format(FILE_UPLOAD_SUCCESS_RESPONSE, Boolean.TRUE, message,fileSize,fileName,fileType);
	}

    
    @RequestMapping(value="create", method=RequestMethod.POST)
    public @ResponseBody
            Map<String, Object> create(EmployeeDTO employeeDTO, HttpServletRequest request,
                                HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        employeeDTO.setCompanyId(sessionData.getCompanyId());
        employeeDTO = employeeService.create(employeeDTO);
        
        return JsonModelMap.success().data(employeeDTO.getFirstName()+" saved successfully!");
    }
    
    @RequestMapping(value="empList", method= RequestMethod.GET)
    public @ResponseBody
            Map<String, Object> getEmployeeList(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        List<EmployeeDTO> empDTOList = employeeService.findAllByCompanyId(sessionData.getCompanyId());

        return JsonModelMap.success().data(empDTOList);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "uploadImage")
    public @ResponseBody
            Map<String, Object> uploadImage(FileUploadBean uploadItem, 
            BindingResult result,
            HttpServletResponse response) throws IOException{
        
        ExtJSFormResult extjsFormResult = new ExtJSFormResult();
        String status = "Success";
        if(result.hasErrors()){
            for(ObjectError error: result.getAllErrors()){
                System.err.println("Error: " + error.getCode() +  " - " + error.getDefaultMessage());
            }
            extjsFormResult.setSuccess(false);
            status = "Failure";
        }

        EmployeeDTO employeeDTO = employeeService.find(uploadItem.getEmployeeId());
        employeeDTO.setPicture(uploadItem.getFile().getBytes());
        employeeService.create(employeeDTO);
        
        response.setContentType("text/html");
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        Writer out = responseWrapper.getWriter();
        String json = buildSuccessFileUploadResonse("File uploaded Successfully", 
                                                    uploadItem.getFile().getSize(),
                                                    uploadItem.getFile().getOriginalFilename(),
                                                    uploadItem.getFile().getContentType());
        out.write(json);
        out.close();

        
        return JsonModelMap.success().data(status);
    }
    
   
}
