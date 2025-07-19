package org.manish07.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration ().configure ().buildSessionFactory ();
        }
        catch (HibernateException e) {
            throw new RuntimeException (e);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
