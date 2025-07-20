package org.manish07.dao;

import org.manish07.model.Room;

import java.math.BigDecimal;
import java.util.List;

public interface RoomDAO extends GenericsDAO<Room> {
    
    List<Room> findAcRoom (List<Room> roomList, String acType);

    List<Room> findCheaperRoom (List<Room> rooms, BigDecimal amount);

    List<Room> findBedRoom(List<Room> rooms,String bedType);

    Room findRoomByRooNo(int roomNo);
    
}
