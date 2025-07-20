package org.manish07.service.impl;

import org.manish07.dao.BookingDAO;
import org.manish07.dao.CustomerDAO;
import org.manish07.dao.RoomDAO;
import org.manish07.model.Booking;
import org.manish07.service.BookingService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

//---------------------------------------------BUSINESS LOGIC IMPLEMENTATION---------------------------------------


public class BookingServiceImpl implements BookingService {
    
    private final CustomerDAO customerDAO;
    private final RoomDAO roomDOA;
    private final BookingDAO bookingDAO;
    
    public BookingServiceImpl (CustomerDAO customerDAO, RoomDAO roomDOA, BookingDAO bookingDAO) {
        this.customerDAO = customerDAO;
        this.roomDOA = roomDOA;
        this.bookingDAO = bookingDAO;
    }
    
    
    @Override
    public boolean bookRoom (Booking booking) {

        int customerId = booking.getCustomer ().getCustomerId ();
        int roomId = booking.getRoom ().getRoomId ();
        LocalDate checkIn = booking.getCheckIn ();
        LocalDate checkOut = booking.getCheckOut ();

        if (roomDOA.findById (roomId) == null || customerDAO.findById (customerId) == null) {
            System.out.println ("Either Room or Customer not found");
            return false;
        }

        if (checkIn.isAfter (checkOut) || checkIn.isBefore (LocalDate.now ()) || checkOut.isBefore (
                LocalDate.now ())) {
            System.out.println ("Check In and Check Out are not a valid date");
            return false;
        }

        List<Booking> bookingList = bookingDAO.findBookingByRoomId (bookingDAO.findAll (),roomId);
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
        bookingDAO.save (booking);
        System.out.println("Booking successful: " + roomId + " from " + checkIn + " to " + checkOut);
        return true;
    }


    @Override
    public List<Booking> getListBookingByCustomerId (int customerId) {
        return bookingDAO.findBookingByCustomerId (bookingDAO.findAll (),customerId);
    }

    @Override
    public List<Booking> getListBookingByRoomId (int roomId) {
        return bookingDAO.findBookingByRoomId (bookingDAO.findAll (),roomId);
    }
    
    @Override
    public List<Booking> getBookingByCustomerId(int customerId){
        return bookingDAO.findBookingByCustomerId (bookingDAO.findAll (),customerId);
    }
    
    @Override
    public boolean save (Booking booking) {
        return bookingDAO.save (booking);
    }
    
    @Override
    public boolean update (Booking booking) {
        return bookingDAO.update(booking);
    }
    
    @Override
    public boolean delete (Booking booking) {
        return bookingDAO.delete (booking);
    }
    
    @Override
    public Booking findById (int id) {
        return bookingDAO.findById (id);
    }
    
    @Override
    public List<Booking> findAll () {
        return bookingDAO.findAll();
    }
}
