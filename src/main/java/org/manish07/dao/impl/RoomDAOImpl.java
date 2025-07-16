package org.manish07.dao.impl;

import org.jetbrains.annotations.NotNull;
import org.manish07.GenericsDOA.GenericDAO;
import org.manish07.dao.RoomDOA;
import org.manish07.model.Room;
import org.manish07.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//------------------------------- C O M M U N I C A T I O N    W I T H   D A T A B A S E ------------------------------

public class RoomDAOImpl implements RoomDOA {
    
    private static final Logger logger = Logger.getLogger (RoomDAOImpl.class.getName ());
    private final Connection connection = DBUtil.getConnection ();
    GenericDAO dao = new GenericDAO (connection);
    
    public static List<Room> fromResultSetList (ResultSet rs) throws SQLException {
        List<Room> roomList = new ArrayList<> ();
        
        while (rs.next ()) {
            Room room = new Room (rs.getInt ("id"),
                      rs.getInt ("room_number"),
                      rs.getString ("ac_type"),
                      rs.getBigDecimal ("price"),
                      rs.getString ("bed"),
                      rs.getString ("balcony")
            );
            roomList.add (room);
        }
        return roomList;
    }
    
    public static Room fromResultSet (@NotNull ResultSet rs) throws SQLException {
        return new Room (rs.getInt ("id"),
                         rs.getInt ("room_number"),
                         rs.getString ("ac_type"),
                         rs.getBigDecimal ("price"),
                         rs.getString ("bed"),
                         rs.getString ("balcony")
        );
    }
    
    @Override
    public boolean addRoom (Room room) {
        
        try (PreparedStatement ps = connection.prepareStatement (
                "INSERT INTO rooms (room_number ,price ,ac_type ,bed ,balcony) " +
                        "VALUES (?,?,?,?,?)")){
            
            ps.setInt (1, room.getRoomNumber ());
            ps.setBigDecimal (2, room.getPrice ());
            ps.setString (3, room.getAcType ());
            ps.setString (4, room.getBed ());
            ps.setString (5, room.getBalcony ());
            
            return ps.executeUpdate () > 0;
            
        } catch (SQLException e) {
            logger.log (Level.SEVERE, "Error Adding Room to the database", e);
            return false;
        }
    }
    
    @Override
    public Room getRoomById (int roomId) {
//--------------------------------------------------------------------------------------------------
        // Method Number 1

//        String query = "SELECT * FROM rooms WHERE id = ?";
//        try{
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, roomId);
//            ResultSet rs = preparedStatement.executeQuery();
//            if(rs.next()){
//                int id = rs.getInt("id");
//                int roomNo = rs.getInt("room_number");
//                String ac = rs.getString("ac_type");
//                BigDecimal price = rs.getBigDecimal("price");
//                String bed = rs.getString("bed");
//                String balcony = rs.getString("balcony");
//
//                return new Room(id,roomNo,ac,price,bed,balcony);
//            }
//            return null;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        
        //--------------------------------------------------------------------------------------------------
        
        // Method Number 2

//        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM rooms WHERE id = ?")){
//            ps.setInt(1, roomId);
//            ResultSet rs = ps.executeQuery();
//            return rs.next() ? new Room(rs.getInt("id"),rs.getInt("room_number"),
//                                        rs.getString("ac_type"), rs.getBigDecimal("price"),
//                                        rs.getString("bed"),rs.getString("balcony")) : null;
//        } catch (SQLException e){
//            e.printStackTrace();[]
//        }
        
        //--------------------------------------------------------------------------------------------------

//        Method 3 : (USING GENERICS)

//        1.CREATING A INTERFACE @FUNCTIONAL INTERFACE : This interface defines how to convert a ResultSet into an object of type T
//        public interface RowMapper<T> {
//              T map(ResultSet rs) throws SQLException;  }
//
//        2.CREATING A CLASS CALLED GenericDAO : CONSTRUCTOR WILL TAKE CONNECTION AND INITIALIZE TO ITS VARIABLE

//        public <T> T getById(String sql, int id, RowMapper<T> mapper) {
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            return rs.next() ? mapper.map(rs) : null;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//      }
//
//  ----------  AT END  -----------
//        @Override
//        public Room getRoomById(int roomId) {
//            String sql = "SELECT * FROM rooms WHERE id = ?";
//            GenericDAO dao = new GenericDAO(connection);
//
//            return dao.getById(sql, roomId, rs -> new Room(
//                    rs.getInt("id"),
//                    rs.getInt("room_number"),
//                    rs.getString("ac_type"),
//                    rs.getBigDecimal("price"),
//                    rs.getString("bed"),
//                    rs.getString("balcony")
//            ));
//        }
        
        //--------------------------------------------------------------------------------------------------

//          METHOD 4
//        SAME AS METHOD 3 JUST REMOVE
//        rs.getInt("id"),
//                   rs.getInt("room_number"),
//                   rs.getString("ac_type"),
//                   rs.getBigDecimal("price"),
//                    rs.getString("bed"),
//                    rs.getString("balcony")
//        THIS INTO A METHOD AND IT IS RUNNING
//        return dao.query(
//                "SELECT * FROM rooms WHERE id = ?",
//                ps -> ps.setInt(1, roomId),
//                RoomDAOImpl::fromResultSet
//        );
        
        //--------------------------------------------------------------------------------------------------
        return dao.query ("SELECT * FROM rooms WHERE id = ?",
                          ps -> ps.setInt (1, roomId),
                          RoomDAOImpl :: fromResultSet
                         );
    }
    
    @Override
    public List<Room> getAvailableRooms (int id) {
        
        return dao.query ("SELECT * FROM rooms WHERE id > ?",
                          ps -> ps.setInt (1, id),
                          RoomDAOImpl::fromResultSetList
                         );
    }
    @Override
    public List<Room> getACRoom (String ac_type) {
        
        return dao.query ("SELECT * FROM rooms WHERE ac_type = ? ",
                          ps -> ps.setString (1, ac_type),
                          RoomDAOImpl :: fromResultSetList
                         );
    }
    
    @Override
    public List<Room> getNonACRoom (String ac_type) {
        
        
        return dao.query ("SELECT * FROM rooms WHERE ac_type = ? ",
                          ps -> ps.setString (1, ac_type),
                          RoomDAOImpl :: fromResultSetList
                         );
    }
    
    @Override
    public List<Room> getCheaperRoom (BigDecimal price) {
        
        
        return dao.query ("SELECT * FROM rooms WHERE price = ? ",
                          ps -> ps.setBigDecimal (1, price),
                          RoomDAOImpl :: fromResultSetList
                         );
    }
    
    @Override
    public List<Room> getSingleBedRoom (String bed) {
        
        
        return dao.query ("SELECT * FROM rooms WHERE bed = ? ",
                          ps -> ps.setString (1, bed),
                          RoomDAOImpl :: fromResultSetList
                         );
    }
    
    @Override
    public List<Room> getDoubleBedRoom (String bed) {
        
        return dao.query ("SELECT * FROM rooms WHERE bed = ? ",
                          ps -> ps.setString (1, bed),
                          RoomDAOImpl :: fromResultSetList
                         );
    }
    
}


