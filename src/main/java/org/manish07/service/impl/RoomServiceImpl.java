package org.manish07.service.impl;

import org.manish07.dao.impl.CustomerDAOImpl;
import org.manish07.dao.impl.RoomDAOImpl;
import org.manish07.model.Room;
import org.manish07.service.RoomService;

import java.math.BigDecimal;
import java.util.List;


//-----------------------------------------------BUSINESS LOGIC IMPLEMENTATION------------------------------------------


public class RoomServiceImpl implements RoomService {

    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    RoomDAOImpl roomDOA = new RoomDAOImpl();
    
    @Override
    public boolean addRoom(Room room) {
        return roomDOA.addRoom(room);
    }
    
    public Room getRoomById(int roomId){
       return roomDOA.getRoomById(roomId);
    }
    
    @Override
    public List<Room> getAvailableRooms () {
        return roomDOA.getAvailableRooms (1000);
    }
    
    @Override
    public List<Room> getACRoom (String ac_type) {
       return roomDOA.getACRoom ("AC");
    }
    
    @Override
    public List<Room> getNonACRoom (String ac_type) {
        return roomDOA.getNonACRoom ("NON-AC");
    }
    
    @Override
    public List<Room> getCheaperRoom (BigDecimal amount) {
        return roomDOA.getCheaperRoom (amount);
    }
    
    @Override
    public List<Room> getSingleBedRoom (String bed) {
        return roomDOA.getSingleBedRoom ("SINGLE");
    }
    
    @Override
    public List<Room> getDoubleBedRoom (String bed) {
        return roomDOA.getDoubleBedRoom ("DOUBLE");
    }
}
