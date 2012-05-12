/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao.impl;

import com.opgea.visitors.dao.VisitorDAO;
import com.opgea.visitors.domain.entities.Visitor;
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
public class VisitorDAOImpl implements VisitorDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Visitor create(Visitor visitor) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(visitor);
        tx.commit();
        session.close();
        return visitor;
    }

    @Override
    public Visitor update(Visitor visitor) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(visitor);
        tx.commit();
        session.close();
        return visitor;
    }

    @Override
    public Visitor remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Visitor find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Visitor.findById");
        query.setParameter("id", id);
        Visitor visitor = (Visitor) query.uniqueResult();
        return visitor;
    }

    @Override
    public List<Visitor> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Visitor.findAll");
        List<Visitor> visitors =  query.list();
        return visitors;
    }

    @Override
    public List<Visitor> findAllByCompanyId(Long companyId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Visitor.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        List<Visitor> visitors =  query.list();
        return visitors;
    }

    @Override
    public List<Visitor> findAllByEmployeeId(Long employeeId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Visitor.findAllByEmployeeId");
        query.setParameter("employeeId", employeeId);
        List<Visitor> visitors =  query.list();
        return visitors;
    }
    
}
