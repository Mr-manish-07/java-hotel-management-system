package org.manish07.model;


import java.time.*;

public class Booking {

//-------------------------------------------------PLAIN JAVA CLASS(POJO)----------------------------------------------

    private int bookingId, customerId , roomId;
    private LocalDate checkIn , checkOut;
    private LocalDateTime bookingTime ;
    private String bookingStatus;




    //---------------------------------CONSTRUCTOR-----------------------------

    public Booking(int id, int customerId, int roomId, LocalDate checkIn,
                   LocalDate checkOut, LocalDateTime bookingTime, String bookingStatus) {
        this.bookingId = id;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.bookingTime = bookingTime;
        this.bookingStatus = bookingStatus;
    }

    //---------------------------------GETTER & SETTER--------------------------


    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    //------------------------------TO STRING METHOD--------------------------

    @Override
    public String toString() {
        return "Booking [" +
                "\n       bookingId=" + bookingId +
                "\n       customerId=" + customerId +
                "\n       roomId=" + roomId +
                "\n       checkIn=" + checkIn +
                "\n       checkOut=" + checkOut +
                "\n       bookingTime=" + bookingTime +
                "\n]";
    }
}
