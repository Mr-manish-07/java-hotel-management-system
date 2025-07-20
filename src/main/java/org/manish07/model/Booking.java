package org.manish07.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "booking_sequence", initialValue = 30000, allocationSize = 1)
    @Column(name = "booking_id")
    private Integer bookingId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    
    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;
    
    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;
    
    @Column(name = "booking_time", nullable = false, updatable = false)
    private LocalDateTime bookingTime;
    
    @Column(name = "booking_status", nullable = false, length = 20)
    private String bookingStatus;
    
    // ✅ Default constructor (required by Hibernate)
    
    public Booking() {}
    
    // ✅ Parameterized constructor
    
    public Booking(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut, LocalDateTime bookingTime, String bookingStatus) {
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.bookingTime = bookingTime;
        this.bookingStatus = bookingStatus;
    }
    
    // ✅ Getters and Setters
    
    public Integer getBookingId() { return bookingId; }
    public void setBookingId(Integer bookingId) { this.bookingId = bookingId; }
    
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    
    public LocalDate getCheckIn() { return checkIn; }
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
    
    public LocalDate getCheckOut() { return checkOut; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }
    
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
    
    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }
    
    @Override
    public String toString() {
        return "Booking [" +
                "\n    bookingId=" + bookingId +
                "\n    customer=" + (customer != null ? customer.getCustomerId() : null) +
                "\n    room=" + (room != null ? room.getRoomNumber() : null) +
                "\n    checkIn=" + checkIn +
                "\n    checkOut=" + checkOut +
                "\n    bookingTime=" + bookingTime +
                "\n    bookingStatus=" + bookingStatus +
                "\n]";
    }
}
