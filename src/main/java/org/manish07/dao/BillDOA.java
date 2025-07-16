package org.manish07.dao;

import org.manish07.model.Bill;

import java.math.BigDecimal;

public interface BillDOA {

//---------------------------------------------ABSTRACT METHOD OF BILL CLASS--------------------------------------------

    String generateBill(Bill bill) ;

    Bill getBillByBookingId (int bookingId );

    boolean makePayment (int bookingId , BigDecimal amount);

}
