package org.manish07.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomNumberGenerator implements IdentifierGenerator {
    
    private static final AtomicInteger counter = new AtomicInteger(100); // starting number
    
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return counter.getAndIncrement();
    }
}
