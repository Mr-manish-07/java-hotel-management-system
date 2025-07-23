package org.manish07.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {
    
    
    //-------------------------------------------------PLAIN JAVA CLASS(POJO)----------------------------------------------
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int customerId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    /* ---------------------------------CONSTRUCTOR----------------------------- */
    
    public Customer (int id, String name, String phone, String email, LocalDateTime createdAt) {
        this.customerId = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }
    
    public Customer(){}
    
    /* ---------------------------------GETTER & SETTER-------------------------- */
    
    public int getCustomerId () {
        return customerId;
    }
    
    public void setCustomerId (int id) {
        this.customerId = id;
    }
    
    public String getName () {
        return name;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public String getPhone () {
        return phone;
    }
    
    public void setPhone (String phone) {
        this.phone = phone;
    }
    
    public String getEmail () {
        return email;
    }
    
    public void setEmail (String email) {
        this.email = email;
    }
    
    public LocalDateTime getCreatedAt () {
        return createdAt;
    }
    
    public void setCreatedAt (LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    
    //------------------------------TO STRING METHOD--------------------------
    
    @Override
    public String toString () {
        return "_________________________________________________" +
                "\nCustomer[" +
                "\n    customerId=" + customerId +
                "\n    name='" + name + '\'' +
                "\n    phone='" + phone + '\'' +
                "\n    email='" + email + '\'' +
                "\n    createdAt=" + createdAt +
                "]" +
                "\n-------------------------------------------------\n";
    }
}
