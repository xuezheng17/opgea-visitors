/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service;

import com.opgea.visitors.web.dto.CompanyDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface CompanyService {
    
    public CompanyDTO create(CompanyDTO companyDTO);
    public CompanyDTO update(CompanyDTO companyDTO);
    public CompanyDTO remove(Long id);
    public CompanyDTO find(Long id);
    public List<CompanyDTO> findAll();
}
