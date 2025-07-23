package org.manish07.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.manish07.dao.BillDAO;
import org.manish07.model.Bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BillDAOImpl extends GenericsDAOImpl<Bill> implements BillDAO{
    
    public BillDAOImpl (SessionFactory sessionFactory) {
        super (sessionFactory, Bill.class);
    }
    
    public Bill currentBill () {
        return super.findAll ().stream ().min ((a, b) ->
                                                       Integer.compare (b.getBillId (),
                                                                        a.getBillId ()
                                                                       ))
                .orElse (null);
    }
    
    @Override
    public String generateBill (Bill bill) {
        return bill.toString ();
    }
    
    @Override
    public boolean makePayment (int bookingId, BigDecimal amount) {
        Transaction transaction = null;
        
        try (Session session = sessionFactory.openSession ()) {
            transaction = session.beginTransaction();
            
            Bill bill = session.createQuery("FROM Bill WHERE bookingId = :bookingId", Bill.class)
                    .setParameter("bookingId", bookingId)
                    .uniqueResult();
            
            if (bill == null) {
                return false;
            }
            
            // 2. Compare amount with totalAmount
            if (bill.getTotalAmount().compareTo(amount) == 0) {
                // 3. Update payment status & date
                bill.setPaymentStatus("PAID");
                bill.setPaymentDate (LocalDateTime.now ());
                
                session.update(bill);
                transaction.commit();
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Bill findByBookingId (int bookingId) {
        try (Session session = sessionFactory.openSession ()) {
            return session.createQuery("FROM Bill WHERE bookingId = :bookingId", Bill.class)
                    .setParameter("bookingId", bookingId)
                    .uniqueResult();
        }
    }
}