package org.manish07.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class RoomNumberGenerator implements IdentifierGenerator {
    
    @Override
    public Serializable generate (SharedSessionContractImplementor session, Object object) {
        // Get current max room number
        Integer maxRoomNumber = (Integer) session.createQuery("SELECT MAX(r.roomNumber) FROM Room r")
                .uniqueResult();
        
        if (maxRoomNumber == null) {
            return 101; // Start from 101
        }
        
        // Calculate next number
        int nextRoomNumber = maxRoomNumber + 1;
        
        // If it exceeds last in range, jump to next block
        if ((nextRoomNumber % 100) > 10) {
            nextRoomNumber = ((nextRoomNumber / 100) + 1) * 100;
        }
        
        return nextRoomNumber;
    }
}
