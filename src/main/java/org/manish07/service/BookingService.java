package org.manish07.service;

//---------------------------------------------------BUSINESS LOGIC METHODS--------------------------------------------


import org.manish07.dao.GenericsDAO;
import org.manish07.model.Booking;

import java.util.List;

public interface BookingService extends GenericsDAO<Booking> {
    
    boolean bookRoom (Booking booking);
    
    List<Booking> getListBookingByCustomerId (int customerId);
    
    List<Booking> getListBookingByRoomId (int roomId);
    
    List<Booking> getBookingByCustomerId(int customerId);
}
