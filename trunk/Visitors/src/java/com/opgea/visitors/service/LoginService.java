/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service;

import com.opgea.visitors.web.dto.ChangePasswordDTO;
import com.opgea.visitors.web.dto.LoginDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface LoginService {
 
    public LoginDTO create(LoginDTO loginDTO);
    public LoginDTO update(LoginDTO loginDTO);
    public Boolean updatePassword(ChangePasswordDTO changePasswordDTO);
    public LoginDTO remove(String loginId);
    public LoginDTO find(String loginId);
    public Boolean isAuthenticUser(String loginId, String password);
    public List<LoginDTO> findAll();
}
