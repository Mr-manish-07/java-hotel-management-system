package org.manish07.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "visited_customers")
public class VisitedCustomer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visited_seq")
    @SequenceGenerator(name = "visited_seq", sequenceName = "visited_sequence", initialValue = 50000, allocationSize = 1)
    private Integer visitedId;
    
    // ✅ Foreign key to Customer
    @OneToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    
    // ✅ Foreign key to Room
    @OneToOne()
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    
    // ✅ Foreign key to Booking
    @OneToOne()
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
    
    // ✅ Foreign key to Bill
    @OneToOne()
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;
    
    // ✅ Additional snapshot fields for historical data
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "phone", nullable = false)
    private String phone;
    
    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;
    
    @Column(name = "amount_paid", nullable = false)
    private BigDecimal amountPaid;
    
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    
    @Column(name = "booking_time", nullable = false)
    private LocalDateTime bookingTime;
    
    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;
    
    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;
    
    @Column(name = "data_updated_at")
    private LocalDateTime dataUpdatedAt;
    
    // ✅ Default constructor
    public VisitedCustomer() {}
    
    // ✅ Parameterized constructor
    public VisitedCustomer(Customer customer, Room room, Booking booking, Bill bill,
                           String name, String email, String phone, Integer roomNumber,
                           BigDecimal amountPaid, LocalDateTime paymentDate, LocalDateTime bookingTime,
                           LocalDate checkIn, LocalDate checkOut, LocalDateTime dataUpdatedAt) {
        this.customer = customer;
        this.room = room;
        this.booking = booking;
        this.bill = bill;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.roomNumber = roomNumber;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.bookingTime = bookingTime;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.dataUpdatedAt = dataUpdatedAt;
    }
    
    // ✅ Getters & Setters
    public Integer getVisitedId() { return visitedId; }
    public void setVisitedId(Integer visitedId) { this.visitedId = visitedId; }
    
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    
    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }
    
    public Bill getBill() { return bill; }
    public void setBill(Bill bill) { this.bill = bill; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public Integer getRoomNumber() { return roomNumber; }
    public void setRoomNumber(Integer roomNumber) { this.roomNumber = roomNumber; }
    
    public BigDecimal getAmountPaid() { return amountPaid; }
    public void setAmountPaid(BigDecimal amountPaid) { this.amountPaid = amountPaid; }
    
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
    
    public LocalDate getCheckIn() { return checkIn; }
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
    
    public LocalDate getCheckOut() { return checkOut; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }
    
    public LocalDateTime getDataUpdatedAt() { return dataUpdatedAt; }
    public void setDataUpdatedAt(LocalDateTime dataUpdatedAt) { this.dataUpdatedAt = dataUpdatedAt; }
    
    @Override
    public String toString () {
        return "VisitedCustomer [ " +
                "       visitedId=" + visitedId +
                "\n      customer=" + customer +
                "\n      room=" + room +
                "\n      booking=" + booking +
                "\n      bill=" + bill +
                "\n      name='" + name + '\'' +
                "\n      email='" + email + '\'' +
                "\n      phone='" + phone + '\'' +
                "\n      roomNumber=" + roomNumber +
                "\n      amountPaid=" + amountPaid +
                "\n      paymentDate=" + paymentDate +
                "\n      bookingTime=" + bookingTime +
                "\n      checkIn=" + checkIn +
                "\n      checkOut=" + checkOut +
                "\n      dataUpdatedAt=" + dataUpdatedAt +
                " ] ";
    }
}
