package org.manish07.dao;

import org.manish07.model.Booking;

import java.util.List;

public interface BookingDAO extends GenericsDAO<Booking> {
    
    List<Booking> findBookingByCustomerId (List<Booking> bookings, int customerId);
    List<Booking> findBookingByRoomId (List<Booking> bookings, int roomId);
    
}
