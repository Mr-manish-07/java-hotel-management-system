package org.manish07.service;

import org.manish07.model.Bill;

import java.math.BigDecimal;

//---------------------------------------------------BUSINESS LOGIC METHODS--------------------------------------------

public interface BillingService {
    
    String generateBill(Bill bill) ;
    
    Bill getBillByBookingId (int bookingId );
    
    boolean makePayment (int bookingId, BigDecimal amount);
}
