/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao;

import com.opgea.visitors.domain.entities.Country;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface CountryDAO {

    public Country create(Country country);
    public Country update(Country country);
    public Country delete(Long id);
    public Country find(Long id);
    public List<Country> findAll();
}
