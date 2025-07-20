package org.manish07.service.impl;

import org.manish07.Privacy.Encryption;
import org.manish07.dao.*;
import org.manish07.model.*;
import org.manish07.service.BillingService;
import org.manish07.util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


//-----------------------------------------------BUSINESS LOGIC IMPLEMENTATION------------------------------------------


public class BillingServiceImpl implements BillingService {
    
    private final CustomerDAO customerDAO;
    private final RoomDAO roomDAO;
    private final BookingDAO bookingDAO;
    private final BillDAO billDAO;
    private final VisitedCustomerDAO visitedCustomerDAO;
    
    public BillingServiceImpl (CustomerDAO customerDAO, RoomDAO roomDAO, BookingDAO bookingDAO, BillDAO billDAO, VisitedCustomerDAO visitedCustomerDAO) {
        this.customerDAO = customerDAO;
        this.roomDAO = roomDAO;
        this.bookingDAO = bookingDAO;
        this.billDAO = billDAO;
        this.visitedCustomerDAO = visitedCustomerDAO;
    }
    
    @Override
    public String generateBill (Bill bill) {
        
        Booking booking = bookingDAO.findById(bill.getBooking ().getBookingId ());
        
        LocalDate checkIn = booking.getCheckIn ();
        LocalDate checkOut = booking.getCheckOut ();
        
        long days_stayed = DateUtil.getDaysBetween (checkIn, checkOut);
        
        int roomId = booking.getRoom ().getRoomId ();
        
        BigDecimal totalPrice =
                (roomDAO.findById (roomId)
                        .getPrice ()).multiply (BigDecimal.valueOf (days_stayed));
        
        Bill bill1 = new Bill ( bill.getBooking (), totalPrice, "pending",
                               LocalDateTime.now ());
        return billDAO.generateBill (bill1);
    }
    
    @Override
    public Bill getBillByBookingId (int bookingId) {
        return billDAO.findByBookingId (bookingId);
    }
    
    @Override
    public boolean makePayment (int bookingId, BigDecimal amount) {
        
        if(billDAO.makePayment (bookingId, amount)){
            
            System.out.println ("Payment Updated Successfully");
            
            Customer customer = bookingDAO.findById (bookingId).getCustomer ();
            Room room = bookingDAO.findById (bookingId).getRoom ();
            Bill bill = billDAO.findById (bookingId);
            Booking booking = bookingDAO.findById (bookingId);
            String phone =
                    Encryption.decrypt (customerDAO.findById (customer.getCustomerId ()).getPhone ());
            String email =
                    Encryption.decrypt (customerDAO.findById (customer.getCustomerId ()).getEmail ());
            String name = customerDAO.findById ((customer).getCustomerId ()).getName ();
            LocalDateTime paymentDate =
                    billDAO.findByBookingId (bookingId).getPaymentDate();
            LocalDateTime bookingDate = bookingDAO.findById (bookingId).getBookingTime ();
            LocalDate checkIn = bookingDAO.findById (bookingId).getCheckIn ();
            LocalDate checkOut = bookingDAO.findById (bookingId).getCheckOut ();
            
            int roomNo = room.getRoomNumber ();
            
            VisitedCustomer visitedCustomers = new VisitedCustomer (customer, room, booking,
                                                                    bill, name,
                                                                    email, phone ,roomNo,amount,
                                                                    paymentDate,
                                                                    bookingDate, checkIn,
                                                                    checkOut, LocalDateTime.now ());
            if(visitedCustomerDAO.save (visitedCustomers)){
                System.out.println ("Data Handler ( Moved To Server) Updated Successfully");
                
                if(billDAO.delete (billDAO.findByBookingId (bookingId))){
                    System.out.println ("Data Removed From Billing Successfully");
                    if(bookingDAO.delete (bookingDAO.findById (bookingId))){
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
    
    @Override
    public boolean save (Bill bill) {
        return billDAO.save (bill);
    }
    
    @Override
    public boolean update (Bill bill) {
        return billDAO.update (bill);
    }
    
    @Override
    public boolean delete (Bill bill) {
        return billDAO.delete (bill);
    }
    
    @Override
    public Bill findById (int id) {
        return billDAO.findById (id);
    }
    
    @Override
    public List<Bill> findAll () {
        return billDAO.findAll ();
    }
}
