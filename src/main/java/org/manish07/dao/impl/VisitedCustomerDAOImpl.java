package org.manish07.dao.impl;


import org.hibernate.SessionFactory;
import org.manish07.dao.VisitedCustomerDAO;
import org.manish07.model.VisitedCustomer;

public class VisitedCustomerDAOImpl extends GenericsDAOImpl<VisitedCustomer> implements VisitedCustomerDAO {
    
    public VisitedCustomerDAOImpl (SessionFactory sessionFactory) {
        super (sessionFactory, VisitedCustomer.class);
    }
    
}