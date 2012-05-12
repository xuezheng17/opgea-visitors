/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao.impl;

import com.opgea.visitors.dao.DepartmentDAO;
import com.opgea.visitors.domain.entities.Department;
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
public class DepartmentDAOImpl implements DepartmentDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Department create(Department department) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(department);
        tx.commit();
        session.close();
        return department;
    }

    @Override
    public Department update(Department department) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(department);
        tx.commit();
        session.close();
        return department;
    }

    @Override
    public Department remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");        
    }

    @Override
    public Department find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Department.findById");
        query.setParameter("id", id);
        Department Department = (Department) query.uniqueResult();
        session.close();
        return Department;
    }

    @Override
    public List<Department> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Department.findAll");
        List<Department> Departments = query.list();
        return Departments;
    }

    @Override
    public List<Department> findAllByCompanyId(Long companyId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Department.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        List<Department> Departments = query.list();
        return Departments;
    }
    
}
