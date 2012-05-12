/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao.impl;

import com.opgea.visitors.dao.CityDAO;
import com.opgea.visitors.domain.entities.City;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ramesh
 */
@Repository
public class CityDAOImpl implements CityDAO{

    @Autowired
    private SessionFactory sessionFactory;
     
    @Override
    public City create(City city) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public City update(City city) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public City delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    @Override
    public City find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("City.findById");
        query.setParameter("id", id);
        return (City) query.uniqueResult();
    }

    @Override
    public List<City> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("City.findAll");
        System.out.println("Query: "+query.getQueryString());
        return query.list();
    }

    @Override
    public List<City> findAllByCountry(Long countryId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("City.findAllByCountry");
        query.setParameter("id", countryId);
        System.out.println("Query: "+query.getQueryString());
        return query.list();
    }
    
    
    
}
