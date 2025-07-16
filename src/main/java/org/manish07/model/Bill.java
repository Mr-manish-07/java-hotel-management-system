package org.manish07.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bill {

//-------------------------------------------------PLAIN JAVA CLASS(POJO)----------------------------------------------
    
    
    private int billId, bookingId;
    private BigDecimal totalAmount;
    private String paymentStatus;
    private LocalDateTime paymentDate;
    
    
    //---------------------------------CONSTRUCTOR-----------------------------
    
    public Bill (
            int billId, int bookingId, BigDecimal totalAmount,
            String paymentStatus, LocalDateTime paymentDate
                ) {
        this.billId = billId;
        this.bookingId = bookingId;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }
    
    
    //---------------------------------GETTER & SETTER--------------------------
    
    public int getBookingId () {
        return bookingId;
    }
    
    public void setBookingId (int bookingId) {
        this.bookingId = bookingId;
    }
    
    public BigDecimal getTotalAmount () {
        return totalAmount;
    }
    
    public void setTotalAmount (BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getPaymentStatus () {
        return paymentStatus;
    }
    
    public void setPaymentStatus (String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public LocalDateTime getPaymentDate () {
        return paymentDate;
    }
    
    public void setPaymentDate (LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public int getBillId () {
        return billId;
    }
    
    public void setBillId (int billId) {
        this.billId = billId;
    }
    
    
    //------------------------------TO STRING METHOD--------------------------
    
    @Override
    public String toString () {
        return  "\n-------------------------------------------------" +
                "\nBill Id           :" + billId +
                "\nBooking Id        :" + bookingId +
                "\nPayment Date      :" + paymentDate +
                "\nPayment Status    :" + paymentStatus + '\'' +
                "\n-------------------------------------------------" +
                "\nTotal Amount      :"+ totalAmount +
                "\n-------------------------------------------------\n";
    }
}
