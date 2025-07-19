package org.manish07.dao;

import org.manish07.model.Room;

import java.math.BigDecimal;
import java.util.List;

public interface RoomDAO {


//---------------------------------------------ABSTRACT METHOD OF ROOM CLASS--------------------------------------------
    


    boolean addRoom(Room room);

    Room getRoomById(int roomId);

    List<Room> getAvailableRooms(int id);

    List <Room>getACRoom(String ac_type);

    List <Room> getNonACRoom(String ac_type);

    List<Room> getCheaperRoom(BigDecimal amount);

    List<Room> getSingleBedRoom(String bed);

    List<Room> getDoubleBedRoom(String bed);
    
}
