package org.manish07.dao;

import org.manish07.model.Booking;

import java.util.List;

public interface BookingDAO {


//--------------------------------------------ABSTRACT METHOD OF BOOKING CLASS-----------------------------------------

    boolean bookRoom(Booking booking);

    Booking getBookingById(int bookingId);

    List<Booking>  getListBookingByCustomerId (int customerId);
    
    List<Booking>  getListBookingByRoomId (int roomId);
    
 
}
