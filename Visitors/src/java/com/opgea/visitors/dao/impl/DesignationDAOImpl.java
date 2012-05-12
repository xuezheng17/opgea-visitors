/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao.impl;

import com.opgea.visitors.dao.DesignationDAO;
import com.opgea.visitors.domain.entities.Designation;
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
public class DesignationDAOImpl implements DesignationDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Designation create(Designation designation) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(designation);
        tx.commit();
        session.close();
        return designation;
    }

    @Override
    public Designation update(Designation designation) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(designation);
        tx.commit();
        session.close();
        return designation;
    }

    @Override
    public Designation remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");        
    }

    @Override
    public Designation find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Designation.findById");
        query.setParameter("id", id);
        Designation designation = (Designation) query.uniqueResult();
        session.close();
        return designation;
    }

    @Override
    public List<Designation> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Designation.findAll");
        List<Designation> designations = query.list();
        return designations;
    }

    @Override
    public List<Designation> findAllByDepartmentId(Long departmentId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Designation.findAllByDepartmentId");
        query.setParameter("departmentId", departmentId);
        List<Designation> designations = query.list();
        return designations;
    }
    
}
