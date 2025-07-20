package org.manish07.dao;

import org.manish07.model.Bill;

import java.math.BigDecimal;

public interface BillDAO extends GenericsDAO<Bill> {
    
    String generateBill(Bill bill) ;
    
    boolean makePayment (int bookingId , BigDecimal amount);
    
    Bill findByBookingId (int bookingId);
}
