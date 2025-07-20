package org.manish07.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.manish07.dao.CustomerDAO;
import org.manish07.model.Customer;

public class CustomerDAOImpl extends GenericsDAOImpl<Customer> implements CustomerDAO {
    
    public CustomerDAOImpl (SessionFactory sessionFactory) {
        super (sessionFactory, Customer.class);
    }
    
    @Override
    public Customer findByPhoneNumber (String phone) {
        try (Session session = sessionFactory.openSession ()) {
            return session.createQuery("FROM Customer WHERE phone = :phone", Customer.class)
                    .setParameter("phone", phone)
                    .uniqueResult();
        }
    }
    
    @Override
    public Customer findByEmail(String email) {
        try (Session session = sessionFactory.openSession ()) {
            return session.createQuery("FROM Customer WHERE email = :email", Customer.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }
}