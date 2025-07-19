package org.manish07.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
public class Bill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_seq")
    @SequenceGenerator(name = "bill_seq", sequenceName = "bill_sequence", initialValue = 40000, allocationSize = 1)
    private Integer billId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
    
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;
    
    @Column(name = "payment_status", nullable = false, length = 20)
    private String paymentStatus;
    
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    
    // ✅ Default constructor (required by Hibernate)
    public Bill() {}
    
    // ✅ Parameterized constructor
    public Bill(Booking booking, BigDecimal totalAmount, String paymentStatus, LocalDateTime paymentDate) {
        this.booking = booking;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }
    
    // ✅ Getters and Setters
    public Integer getBillId() { return billId; }
    public void setBillId(Integer billId) { this.billId = billId; }
    
    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }
    
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    
    @Override
    public String toString() {
        return "\n-------------------------------------------------" +
                "\nBill Id           : " + billId +
                "\nBooking Id        : " + (booking != null ? booking.getBookingId() : null) +
                "\nPayment Date      : " + paymentDate +
                "\nPayment Status    : " + paymentStatus +
                "\n-------------------------------------------------" +
                "\nTotal Amount      : " + totalAmount +
                "\n-------------------------------------------------\n";
    }
}
