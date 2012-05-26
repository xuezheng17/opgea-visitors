/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao.impl;

import com.opgea.visitors.dao.EmployeeDAO;
import com.opgea.visitors.domain.entities.Employee;
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
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Employee create(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(employee);
        tx.commit();
        session.close();
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(employee);
        tx.commit();
        session.close();
        return employee;
    }

    @Override
    public Employee remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Employee find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Employee.findAllById");
        query.setParameter("id", id);
        Employee employee = (Employee)query.uniqueResult();
        session.close();
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Employee.findAll");
        List<Employee> employees = query.list();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> findAllByCompanyId(Long companyId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Employee e WHERE e.company.id="+companyId);
        List<Employee> employees = query.list();
        System.out.println("Employee List size: "+employees.size());
        session.close();
        return employees;
    }

    @Override
    public List<Employee> findAllByDepartmentId(Long departmentId) {
       Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Employee e WHERE e.department.id="+departmentId);
        List<Employee> employees = query.list();
        System.out.println("Employee List size: "+employees.size());
        session.close();
        return employees;
    }
    
}
