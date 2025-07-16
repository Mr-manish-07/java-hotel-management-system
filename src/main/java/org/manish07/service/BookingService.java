package org.manish07.service;

import org.manish07.model.Booking;

import java.util.List;

//---------------------------------------------------BUSINESS LOGIC METHODS--------------------------------------------


public interface BookingService {
    
    boolean bookRoom (Booking booking);
    
    Booking getBookingById(int bookingId);
    
    List<Booking> getListBookingByCustomerId (int customerId);
    
    List<Booking>  getListBookingByRoomId (int roomId);

}
