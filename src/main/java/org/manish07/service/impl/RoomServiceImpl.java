package org.manish07.service.impl;

import org.manish07.dao.RoomDAO;
import org.manish07.model.Room;
import org.manish07.service.RoomService;

import java.math.BigDecimal;
import java.util.List;


//-----------------------------------------------BUSINESS LOGIC IMPLEMENTATION------------------------------------------


public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDOA;
    
    public RoomServiceImpl(RoomDAO roomDOA) {
        this.roomDOA = roomDOA;
    }
    
    @Override
    public boolean addRoom(Room room) {
        return roomDOA.save (room);
    }
    
    @Override
    public Room getRoomById(int roomId){
       return roomDOA.findById (roomId);
    }
    
    @Override
    public List<Room> getAllRooms () {
        return roomDOA.findAll ();
    }
    
    @Override
    public List<Room> getACRoom (String ac_type) {
       return roomDOA.findAcRoom (roomDOA.findAll (),ac_type);
    }
    
    
    @Override
    public List<Room> getCheaperRoom (BigDecimal amount) {
        return roomDOA.findCheaperRoom (roomDOA.findAll (),amount);
    }
    
    @Override
    public List<Room> getBedRoom (String bed) {
        return roomDOA.findBedRoom (roomDOA.findAll (),bed);
    }
    
    @Override
    public Room getRoomByRooNo(int roomNo){
        return roomDOA.findRoomByRooNo (roomNo);
    }
    
    @Override
    public boolean save (Room room) {
        return roomDOA.save (room);
    }
    
    @Override
    public boolean update (Room room) {
        return roomDOA.update(room);
    }
    
    @Override
    public boolean delete (Room room) {
        return roomDOA.delete (room);
    }
    
    @Override
    public Room findById (int id) {
        return roomDOA.findById (id);
    }
    
    @Override
    public List<Room> findAll () {
        return roomDOA.findAll ();
    }
}
