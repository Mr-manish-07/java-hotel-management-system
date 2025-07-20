package org.manish07.model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
public class Room {


//-------------------------------------------------PLAIN JAVA CLASS(POJO)----------------------------------------------
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "rooms_seq")
    @SequenceGenerator(name = "rooms_seq", sequenceName = "rooms_sequence", initialValue = 20000,
            allocationSize = 1)
    private int roomId;
    
    @Column(name = "room_number", unique = true, nullable = false)
    private int roomNumber;
    
    @Column(name = "ac_type" , nullable = false)
    private String acType ;
    
    @Column(nullable = false)
    private BigDecimal price ;
    
    @Column(nullable = false)
    private String bed;
    
    @Column(nullable = false)
    private String balcony;

    //---------------------------------CONSTRUCTOR-----------------------------

    

    public Room(int id, int roomNumber, String type, BigDecimal price, String bed, String balcony) {
        this.roomId = id;
        this.roomNumber = roomNumber;
        this.acType = type;
        this.price = price;
        this.bed = bed;
        this.balcony = balcony;
    }
    
    public Room () {
    
    }
    
    //---------------------------------GETTER & SETTER--------------------------


    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getBalcony() {
        return this.balcony;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int id) {
        this.roomId = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    //------------------------------TO STRING METHOD--------------------------

    @Override
    public String toString() {
        return "\nRoom  [   " +
                "roomId = " + roomId +
                ",      roomNumber = " + roomNumber +
                ",      price = " + price +
                ",      bad = " +  bed+
                ",      Ac Type= " + acType +
                ",      balcony = " + balcony +
                "   ]";
    }
}
