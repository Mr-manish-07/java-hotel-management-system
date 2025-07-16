package org.manish07.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Visited_Customers {
    private int visited_Id;
    private int booking_Id;
    private int customer_id;
    private int room_id;
    private int bill_id;
    private String phone;
    private String name;
    private String email;
    private BigDecimal amount_paid;
    private LocalDateTime payment_date;
    private LocalDateTime booking_time;
    private LocalDate check_in;
    private LocalDate check_out;
    private LocalDateTime data_Updated_at;
    
    public Visited_Customers (int visited_Id, int booking_Id, int customer_id, int room_id,
                              int bill_id, String phone, String name, String email, BigDecimal amount_paid,
                              LocalDateTime payment_date, LocalDateTime booing_time, LocalDate check_in,
                              LocalDate check_out, LocalDateTime data_Updated_at) {
        this.visited_Id = visited_Id;
        this.booking_Id = booking_Id;
        this.customer_id = customer_id;
        this.room_id = room_id;
        this.bill_id = bill_id;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.amount_paid = amount_paid;
        this.payment_date = payment_date;
        this.booking_time = booing_time;
        this.check_in = check_in;
        this.check_out = check_out;
        this.data_Updated_at = data_Updated_at;
    }
    
    public int getVisited_Id () {
        return visited_Id;
    }
    
    public void setVisited_Id (int visited_Id) {
        this.visited_Id = visited_Id;
    }
    
    public int getBooking_Id () {
        return booking_Id;
    }
    
    public void setBooking_Id (int booking_Id) {
        this.booking_Id = booking_Id;
    }
    
    public int getCustomer_id () {
        return customer_id;
    }
    
    public void setCustomer_id (int customer_id) {
        this.customer_id = customer_id;
    }
    
    public int getRoom_id () {
        return room_id;
    }
    
    public void setRoom_id (int room_id) {
        this.room_id = room_id;
    }
    
    public int getBill_id () {
        return bill_id;
    }
    
    public void setBill_id (int bill_id) {
        this.bill_id = bill_id;
    }
    
    public String getPhone () {
        return phone;
    }
    
    public void setPhone (String phone) {
        this.phone = phone;
    }
    
    public String getName () {
        return name;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public String getEmail () {
        return email;
    }
    
    public void setEmail (String email) {
        this.email = email;
    }
    
    public BigDecimal getAmount_paid () {
        return amount_paid;
    }
    
    public void setAmount_paid (BigDecimal amount_paid) {
        this.amount_paid = amount_paid;
    }
    
    public LocalDateTime getPayment_date () {
        return payment_date;
    }
    
    public void setPayment_date (LocalDateTime payment_date) {
        this.payment_date = payment_date;
    }
    
    public LocalDateTime getBooking_time () {
        return booking_time;
    }
    
    public void setBooing_time (LocalDateTime booing_time) {
        this.booking_time = booing_time;
    }
    
    public LocalDate getCheck_in () {
        return check_in;
    }
    
    public void setCheck_in (LocalDate check_in) {
        this.check_in = check_in;
    }
    
    public LocalDate getCheck_out () {
        return check_out;
    }
    
    public void setCheck_out (LocalDate check_out) {
        this.check_out = check_out;
    }
    
    public LocalDateTime getData_Updated_at () {
        return data_Updated_at;
    }
    
    public void setData_Updated_at (LocalDateTime data_Updated_at) {
        this.data_Updated_at = data_Updated_at;
    }
}
