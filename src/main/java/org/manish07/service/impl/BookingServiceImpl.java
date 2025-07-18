package org.manish07.service.impl;

import org.manish07.dao.impl.BookingDAOImpl;
import org.manish07.dao.impl.CustomerDAOImpl;
import org.manish07.dao.impl.RoomDAOImpl;
import org.manish07.model.Booking;
import org.manish07.service.BookingService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

//---------------------------------------------BUSINESS LOGIC IMPLEMENTATION---------------------------------------


public class BookingServiceImpl implements BookingService {
    
    CustomerDAOImpl customerDAO = new CustomerDAOImpl ();
    RoomDAOImpl roomDOA = new RoomDAOImpl ();
    BookingDAOImpl bookingDOA = new BookingDAOImpl ();
    
    
    @Override
    public boolean bookRoom (Booking booking) {
        
        int customerId = booking.getCustomerId ();
        int roomId = booking.getRoomId ();
        LocalDate checkIn = booking.getCheckIn ();
        LocalDate checkOut = booking.getCheckOut ();
        
        if (roomDOA.getRoomById (roomId) == null || customerDAO.getCustomerById (customerId) == null) {
            System.out.println ("Either Room or Customer not found");
            return false;
        }
        
        if (checkIn.isAfter (checkOut) || checkIn.isBefore (LocalDate.now ()) || checkOut.isBefore (
                LocalDate.now ())) {
            System.out.println ("Check In and Check Out are not a valid date");
            return false;
        }
        
        List<Booking> bookingList = bookingDOA.getListBookingByRoomId (roomId);
        bookingList.sort (Comparator.comparing (Booking :: getCheckIn));
        
        for (Booking existing : bookingList) {
            LocalDate existingIn = existing.getCheckIn ();
            LocalDate existingOut = existing.getCheckOut ();
            
            if (checkIn.isBefore (existingOut) && checkOut.isAfter (existingIn)) {
                System.out.println ("Booking Not Allowed  from " + existingIn + " " +
                                            "to " + existingOut);
                return false;
            }
        }
        bookingDOA.bookRoom(booking);
        System.out.println("Booking successful: " + roomId + " from " + checkIn + " to " + checkOut);
        return true;
    }
    
    @Override
    public Booking getBookingById (int bookingId) {
       return bookingDOA.getBookingById (bookingId);
    }
    
    @Override
    public List<Booking> getListBookingByCustomerId (int customerId) {
        return bookingDOA.getListBookingByCustomerId (customerId);
    }
    
    @Override
    public List<Booking> getListBookingByRoomId (int roomId) {
        return bookingDOA.getListBookingByRoomId (roomId);
    }
    
    public Booking getBookingByBookingId(int bookingId){
        return bookingDOA.getBookingById (bookingId);
    }
    
    public Booking getBookingByCustomerId(int customerId){
        return bookingDOA.getBookingByCustomerId (customerId);
    }
}
