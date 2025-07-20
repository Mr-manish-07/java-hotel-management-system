package org.manish07.dao.impl;

import org.hibernate.SessionFactory;
import org.manish07.dao.BookingDAO;
import org.manish07.dao.GenericsDAO;
import org.manish07.dao.impl.GenericsDAOImpl;
import org.manish07.model.Booking;

import java.util.List;

public class BookingDAOImpl extends GenericsDAOImpl<Booking> implements BookingDAO {
    
    public BookingDAOImpl (SessionFactory sessionFactory) {
        super(sessionFactory, Booking.class);
    }
    
    
    @Override
    public List<Booking> findBookingByCustomerId (List<Booking> bookings, int customerId) {
        return bookings.stream ()
                .filter (booking -> booking.getCustomer ().getCustomerId () == customerId)
                .toList ();
    }

    @Override
    public List<Booking> findBookingByRoomId (List<Booking> bookings, int roomId) {
        return bookings.stream ()
                .filter (booking -> booking.getRoom ().getRoomId () == roomId)
                .toList ();
    }
}


