package org.manish07.dao;

import java.util.List;

public interface GenericsDAO <T>{
    
    boolean save(T t);
    
    boolean  update(T t);
    
    boolean delete(T t);
    
    T findById(int id);
    
    List<T> findAll();
    
}
