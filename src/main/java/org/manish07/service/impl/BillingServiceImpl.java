package org.manish07.service.impl;

import org.manish07.Privacy.Encryption;
import org.manish07.dao.impl.*;
import org.manish07.model.Bill;
import org.manish07.model.Visited_Customers;
import org.manish07.service.BillingService;
import org.manish07.util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


//-----------------------------------------------BUSINESS LOGIC IMPLEMENTATION------------------------------------------


public class BillingServiceImpl implements BillingService {
    
    CustomerDAOImpl customerDAO = new CustomerDAOImpl ();
    RoomDAOImpl roomDOA = new RoomDAOImpl ();
    BookingDAOImpl bookingDOA = new BookingDAOImpl ();
    BillDAOImpl billDOA = new BillDAOImpl ();
    VisitedCustomerDoaImpl visitedCustomerDoa = new VisitedCustomerDoaImpl ();
    
    
    @Override
    public String generateBill (Bill bill) {
        
        LocalDate checkIn = bookingDOA.getBookingById (bill.getBookingId ()).getCheckIn ();
        LocalDate checkOut = bookingDOA.getBookingById (bill.getBookingId ()).getCheckOut ();
        
        long days_stayed = DateUtil.getDaysBetween (checkIn, checkOut);
        
        int roomId = bookingDOA.getBookingById (bill.getBookingId ()).getRoomId ();
        
        BigDecimal totalPrice =
                (roomDOA.getRoomById (roomId)
                        .getPrice ()).multiply (BigDecimal.valueOf (days_stayed));
        
        Bill bill1 = new Bill (bill.getBillId (), bill.getBookingId (), totalPrice, "pending",
                               LocalDateTime.now ());
        return billDOA.generateBill (bill1);
    }
    
    @Override
    public Bill getBillByBookingId (int bookingId) {
        return billDOA.getBillByBookingId (bookingId);
    }
    
    @Override
    public boolean makePayment (int bookingId, BigDecimal amount) {
        
        if(billDOA.makePayment (bookingId, amount)){
            System.out.println ("Payment Updated Successfully");
            
            int customerId = bookingDOA.getBookingById (bookingId).getCustomerId ();
            int roomId = bookingDOA.getBookingById (bookingId).getRoomId ();
            int billId = billDOA.getBillByBookingId (bookingId).getBillId ();
            String phone =
                    Encryption.decrypt (customerDAO.getCustomerById (customerId).getPhone ());
            String email =
                    Encryption.decrypt (customerDAO.getCustomerById (customerId).getEmail ());
            String name = customerDAO.getCustomerById (customerId).getName ();
            LocalDateTime paymentDate =
                    billDOA.getBillByBookingId(bookingId).getPaymentDate();
            LocalDateTime bookingDate = bookingDOA.getBookingById (bookingId).getBookingTime ();
            LocalDate checkIn = bookingDOA.getBookingById (bookingId).getCheckIn ();
            LocalDate checkOut = bookingDOA.getBookingById (bookingId).getCheckOut ();
            
            Visited_Customers visitedCustomers = new Visited_Customers (1,bookingId,customerId,
                                                                        roomId,billId,phone,name,
                                                                        email,amount,paymentDate,
                                                                        bookingDate,checkIn,
                                                                        checkOut,LocalDateTime.now ());
            if(visitedCustomerDoa.dataHandler (visitedCustomers)){
                System.out.println ("Data Handler ( Moved To Server) Updated Successfully");
                
                if(billDOA.deleteDateByBookingId (bookingId)){
                    System.out.println ("Data Removed From Billing Successfully");
                    if(bookingDOA.deleteByBookingId (bookingId)){
                        System.out.println ("Data Deleted From Booking Successfully");
                    }
                }
                
            }
            return true;
            
        }else {
            System.out.println ("Failed , Please Pay Full Amount of Bill Generated");
            return false;
        }
    }
    
}
