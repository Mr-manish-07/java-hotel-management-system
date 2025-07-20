package org.manish07.service;

//---------------------------------------------------BUSINESS LOGIC METHODS--------------------------------------------


import org.manish07.model.Room;

import java.math.BigDecimal;
import java.util.List;

public interface RoomService {
    
    boolean addRoom (Room room);
    
    Room getRoomById (int roomId);
    
    List<Room> getAllRooms ();
    
    List<Room> getACRoom (String ac_type);
    
    List<Room> getCheaperRoom (BigDecimal amount);
    
    List<Room> getBedRoom (String bed);
    
    Room getRoomByRooNo (int roomNo);

}
