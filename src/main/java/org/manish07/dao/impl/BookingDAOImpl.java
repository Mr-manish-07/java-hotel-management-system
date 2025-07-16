package org.manish07.dao.impl;

import org.manish07.GenericsDOA.GenericDAO;
import org.manish07.dao.BookingDOA;
import org.manish07.model.Booking;
import org.manish07.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//------------------------------- C O M M U N I C A T I O N    W I T H   D A T A B A S E ------------------------------

public class BookingDAOImpl implements BookingDOA {
    
    //---------- INSTANCE VARIABLE AND OBJECT OF OTHER CLASSES ------------
    
    private static final Logger logger = Logger.getLogger (BookingDAOImpl.class.getName ());
    private final Connection connection = DBUtil.getConnection ();
    GenericDAO dao = new GenericDAO (connection);

//-----------------------------------------------------BOOK ROOM ------------------------------------------------------
    
    @Override
    public boolean bookRoom (Booking booking) {
        
        try (PreparedStatement ps = connection.prepareStatement (
                "INSERT INTO bookings(customer_id,room_id,check_in,check_out,booking_time,booking_status)" +
                        "VALUES(?,?,?,?,?,?)")) {
            
            ps.setInt (1, booking.getCustomerId ());
            ps.setInt (2, booking.getRoomId ());
            ps.setDate (3, java.sql.Date.valueOf (booking.getCheckIn ()));
            ps.setDate (4, java.sql.Date.valueOf (booking.getCheckOut ()));
            ps.setTimestamp (5, java.sql.Timestamp.valueOf (booking.getBookingTime ()));
            ps.setString (6, booking.getBookingStatus ());
            
            return ps.executeUpdate () > 0;
        }
        catch (SQLException e) {
            logger.log (Level.SEVERE, "Error adding room to the database", e);
            return false;
        }
    }

//-----------------------------------------------------GET BOOKING BY ID-----------------------------------------------
    
    @Override
    public Booking getBookingById (int bookingId) {
        
        try (PreparedStatement ps = connection.prepareStatement (
                "SELECT * FROM bookings WHERE id = ?")) {
            
            ps.setInt (1, bookingId);
            
            ResultSet rs = ps.executeQuery ();
            
            return rs.next () ? new Booking (rs.getInt ("id"),
                                             rs.getInt ("customer_id"),
                                             rs.getInt ("room_id"),
                                             rs.getDate ("check_in").toLocalDate (),
                                             rs.getDate ("check_out").toLocalDate (),
                                             rs.getTimestamp ("booking_time").toLocalDateTime (),
                                             rs.getString ("booking_status")
            ) : null;
        }
        catch (SQLException e) {
            logger.log (Level.SEVERE, "Error adding customer to the database", e);
            return null;
        }
    }
    

//-----------------------------------------------------GET CUSTOMER'S BOOKING------------------------------------------
    
    @Override
    public List<Booking> getListBookingByCustomerId (int customerId) {
        
        try (PreparedStatement ps = connection.prepareStatement (
                "SELECT * FROM bookings WHERE customer_id = ?")) {
            
            List<Booking > listOfBooking = new ArrayList<> ();
            
            ps.setInt (1, customerId);
            
            ResultSet rs = ps.executeQuery ();
            
            while (rs.next ()){
                new Booking (rs.getInt ("id"),
                             customerId,
                             rs.getInt ("room_id"),
                             rs.getDate ("check_in").toLocalDate (),
                             rs.getDate ("check_out").toLocalDate (),
                             rs.getTimestamp ("booking_time").toLocalDateTime (),
                             rs.getString ("booking_status")
                );
            };
            return listOfBooking;
        }
        catch (SQLException e) {
            logger.log (Level.SEVERE, "Error get booking from customer to the database", e);
            return null;
        }
    }
    
    @Override
    public List<Booking> getListBookingByRoomId (int roomId) {
        
        try (PreparedStatement ps = connection.prepareStatement (
                "SELECT * FROM bookings WHERE customer_id = ?")) {
            
            List<Booking > listOfBooking = new ArrayList<> ();
            
            ps.setInt (1, roomId);
            
            ResultSet rs = ps.executeQuery ();
            
            while (rs.next ()){
                new Booking (rs.getInt ("id"),
                             rs.getInt ("customer_id"),
                             roomId,
                             rs.getDate ("check_in").toLocalDate (),
                             rs.getDate ("check_out").toLocalDate (),
                             rs.getTimestamp ("booking_time").toLocalDateTime (),
                             rs.getString ("booking_status")
                );
            };
            return listOfBooking;
        }
        catch (SQLException e) {
            logger.log (Level.SEVERE, "Error get booking from customer to the database", e);
            return null;
        }
    }
    
}



