package org.manish07.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.manish07.dao.GenericsDAO;

import java.util.List;

public class GenericsDAOImpl<T> implements GenericsDAO<T> {
    
    protected final SessionFactory sessionFactory;
    private final Class<T> entityClass;
    
    public GenericsDAOImpl(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }
    
    
    @Override
    public boolean save (T entity) {
        
        boolean result = false;
        Transaction tx = null;
        
        try (Session session = sessionFactory.openSession ()) {
            
            tx = session.beginTransaction ();
            session.persist (entity);
            tx.commit ();
            result = true;
            
        }
        catch (Exception e) {
            
            if (tx != null) tx.rollback ();
            e.printStackTrace ();
        }
        return result;
    }
    
    
    @Override
    public boolean update (T entity) {
        
        boolean result = false;
        Transaction tx = null;
        
        try (Session session = sessionFactory.openSession ()) {
            
            tx = session.beginTransaction ();
            session.merge (entity);
            tx.commit ();
            result = true;
            
        }
        catch (Exception e) {
            
            if (tx != null) tx.rollback ();
            e.printStackTrace ();
        }
        return result;
    }
    
    
    @Override
    public boolean delete (T entity) {
        
        boolean result = false;
        Transaction tx = null;
        
        try (Session session = sessionFactory.openSession ()) {
            
            tx = session.beginTransaction ();
            
            if (entity != null) {
                session.remove (entity);  // I'm using JPA standard for deletion
                result = true;
            }
            tx.commit ();
            
        }
        catch (Exception e) {
            
            if (tx != null) tx.rollback ();
            e.printStackTrace ();
        }
        return result;
    }
    
    
    @Override
    public T findById (int id) {
        
        try (Session session = sessionFactory.openSession ()) {
            return session.find (entityClass,id);
        }
    }
    
    
    @Override
    public List<T> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + entityClass.getSimpleName(), entityClass)
                    .getResultList();
        }
    }
    
}
