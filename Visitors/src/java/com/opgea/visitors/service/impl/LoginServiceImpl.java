/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.impl;

import com.opgea.visitors.dao.EmployeeDAO;
import com.opgea.visitors.dao.LoginDAO;
import com.opgea.visitors.domain.entities.Employee;
import com.opgea.visitors.domain.entities.Login;
import com.opgea.visitors.domain.modal.EmployeeStatus;
import com.opgea.visitors.domain.qualifier.OnlineQualifier;
import com.opgea.visitors.service.ApplicationService;
import com.opgea.visitors.service.LoginService;
import com.opgea.visitors.web.dto.LoginDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginDAO loginDAO;
    
    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Autowired
    private ApplicationService applicationService;

    @Override
    public LoginDTO create(LoginDTO loginDTO) {
        Employee employee = employeeDAO.find(loginDTO.getEmployeeId());
        Login login = new Login();
        login.setLoginId(loginDTO.getLoginId());
        login.setPassword(loginDTO.getPassword());
        login.setEmployee(employee);
        loginDAO.create(login);
        return loginDTO;
    }

    @Override
    public LoginDTO update(LoginDTO loginDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LoginDTO remove(String loginId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LoginDTO find(String loginId) {
        Login login = loginDAO.find(loginId);
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmployeeId(login.getEmployee().getId());
        loginDTO.setLoginId(login.getLoginId());
        loginDTO.setPassword(login.getPassword());
        return loginDTO;
    }

    @Override
    public List<LoginDTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    @Override
    public Boolean isAuthenticUser(String loginId, String password) {
        Boolean authentic = false;
        Login login = loginDAO.find(loginId);
        if(login != null){
            if(login.getPassword().equals(password) && login.getIsActive() == true){
                authentic = true;
                EmployeeStatus employeeStatus = new EmployeeStatus();
                employeeStatus.setCompanyId(login.getEmployee().getCompany().getId());
                employeeStatus.setEmployeeId(login.getEmployee().getId());
                employeeStatus.setOnlineStatus(OnlineQualifier.ONLINE);
                applicationService.addEmployeeStatus(employeeStatus);
            }
        }
        return authentic;
    }
    
}
