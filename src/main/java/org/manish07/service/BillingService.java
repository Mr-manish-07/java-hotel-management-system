package org.manish07.service;

//---------------------------------------------------BUSINESS LOGIC METHODS--------------------------------------------

import org.manish07.dao.GenericsDAO;
import org.manish07.model.Bill;

import java.math.BigDecimal;

public interface BillingService extends GenericsDAO<Bill> {
    
    String generateBill (Bill bill);
    
    Bill getBillByBookingId (int bookingId);
    
    boolean makePayment (int bookingId, BigDecimal amount);

}
