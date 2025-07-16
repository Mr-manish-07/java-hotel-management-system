package org.manish07.dao.impl;


import org.manish07.model.Visited_Customers;
import org.manish07.util.DBUtil;

import java.sql.*;
import java.util.logging.Logger;

public class VisitedCustomerDoaImpl {
    
    private static final Logger logger = Logger.getLogger (VisitedCustomerDoaImpl.class.getName ());
    private final Connection connection = DBUtil.getConnection ();
    
    public boolean dataHandler (Visited_Customers visitedCustomers) {

        try(PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO visited_customers(customer_id,room_id, booking_id,bill_id," +
                         "customer_phone,customer_email,amount_paid,payment_date,booking_time," +
                         "checkin_time,checkout_time,name) VALUES " +
                         "(?,?,?,?,?,?,?,?,?,?,?,?)")) {
            
            ps.setInt(1, visitedCustomers.getCustomer_id());
            ps.setInt(2, visitedCustomers.getRoom_id());
            ps.setInt (3,visitedCustomers.getBooking_Id ());
            ps.setInt (4,visitedCustomers.getBill_id ());
            ps.setString (5,visitedCustomers.getPhone ());
            ps.setString (6,visitedCustomers.getEmail ());
            ps.setBigDecimal (7,visitedCustomers.getAmount_paid ());
            ps.setTimestamp (8, Timestamp.valueOf(visitedCustomers.getPayment_date ()));
            ps.setTimestamp (9, Timestamp.valueOf(visitedCustomers.getBooking_time ()));
            ps.setDate (10, java.sql.Date.valueOf(visitedCustomers.getCheck_in()));
            ps.setDate (11, java.sql.Date.valueOf(visitedCustomers.getCheck_out ()));
            ps.setString (12,visitedCustomers.getName());
            
            return ps.executeUpdate () > 0;

        }catch (SQLException e){
            logger.warning ("Error while adding visited customer to database : "+e.getMessage());
            return false;
        }

    }
}