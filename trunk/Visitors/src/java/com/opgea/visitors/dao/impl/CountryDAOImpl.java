/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao.impl;

import com.opgea.visitors.dao.CountryDAO;
import com.opgea.visitors.domain.entities.Country;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ramesh
 */
@Repository
public class CountryDAOImpl implements CountryDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Country create(Country country) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(country);
        tx.commit();
        return country;
    }

    @Override
    public Country update(Country country) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Country delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Country find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Country.findById");
        query.setParameter("id", id);
        return (Country) query.uniqueResult();
    }

    @Override
    public List<Country> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Country.findAll");
        System.out.println("Query: "+query.getQueryString());
        return query.list();
    }
    
}
