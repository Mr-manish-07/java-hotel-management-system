package org.manish07.dao.impl;

import org.manish07.dao.BillDAO;
import org.manish07.model.Bill;
import org.manish07.util.DBUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;



public class BillDAOImpl implements BillDAO {
    
    private final Connection connection = DBUtil.getConnection ();
//    GenericDAO dao = new GenericDAO(connection);
//    BookingDAOImpl bookingDOA = new BookingDAOImpl();
//    private static final Logger logger = Logger.getLogger(BillDAOImpl.class.getName());


//--------------------------------------------------GENERATING BILL---------------------------------------------------
    
    @Override
    public String generateBill (Bill bill) {
        
        String query = "INSERT INTO bills(booking_id,total_amount,payment_status,payment_date) VALUES(?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement (query);
            preparedStatement.setInt (1, bill.getBookingId ());
            preparedStatement.setBigDecimal (2, bill.getTotalAmount ());
            preparedStatement.setString (3, bill.getPaymentStatus ());
            preparedStatement.setTimestamp (4, Timestamp.valueOf (bill.getPaymentDate ()));
            
            int rowAffected = preparedStatement.executeUpdate ();
            
            if (rowAffected > 0) {
                
                int billId = getBillByBookingId (bill.getBookingId ()).getBillId ();
                
                return "\n----------------------------------------------------" +
                        "\nBill Id        : " + billId + "\n" +
                        "Payment status : " + bill.getPaymentStatus () + "\n" +
                        "Payment Date   : " + bill.getPaymentDate () + "\n" +
                        "----------------------------------------------------\n" +
                        "Total Price    : ₹" + bill.getTotalAmount () + "\n" +
                        "----------------------------------------------------\n";
            }
            return null;
        }
        catch (SQLException | NumberFormatException e) {
            
            System.out.println (e.getMessage ());
        }
        return null;
    }


//--------------------------------------------------GET BILL BY BOOKING ID---------------------------------------------
    
    @Override
    public Bill getBillByBookingId (int bookingId) {
        
        String query = "select * from bills where booking_id = ?";
        
        try {
            
            PreparedStatement preparedStatement = connection.prepareStatement (query);
            preparedStatement.setInt (1, bookingId);
            
            ResultSet resultSet = preparedStatement.executeQuery ();
            
            if (resultSet.next ()) {
                
                return new Bill (resultSet.getInt ("id"),
                                 resultSet.getInt ("booking_id"),
                                 resultSet.getBigDecimal ("total_amount"),
                                 resultSet.getString ("payment_status"),
                                 resultSet.getTimestamp ("payment_date").toLocalDateTime ()
                );
            }
            return null;
        }
        catch (SQLException e) {
            System.out.println (e.getMessage ());
        }
        return null;
    }


//--------------------------------------------------UPDATING PAYMENT STATUS--------------------------------------------
    
    @Override
    public boolean makePayment (int bookingId, BigDecimal amount) {
        
        BigDecimal totalRemainingAmount =
                getBillByBookingId (bookingId).getTotalAmount ().setScale (0, RoundingMode.DOWN);
        
        int billId = getBillByBookingId (bookingId).getBillId ();
        
        if(totalRemainingAmount.compareTo (amount) != 0) {
            return false;
        } else {
            try(PreparedStatement ps = connection.prepareStatement (
                    "UPDATE bills set payment_status = 'PAID' where booking_id = ? ")){
                
                ps.setInt(1, bookingId);
                return ps.executeUpdate () > 0;
                
            }catch(SQLException e){
                System.out.println (e.getMessage ());
                return false;
            }
        }
    }
    
    
    public boolean deleteDateByBookingId (int bookingId) {
        try(PreparedStatement ps = connection.prepareStatement ("DELETE FROM bills WHERE " +
                                                                        "booking_id = ? ")){
            ps.setInt (1, bookingId);
            return ps.executeUpdate () > 0;
            
        }catch(SQLException e){
            System.out.println (e.getMessage ());
            return false;
        }
    }
    
    
    
    
}

