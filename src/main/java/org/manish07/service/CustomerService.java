package org.manish07.service;

//---------------------------------------------------BUSINESS LOGIC METHODS--------------------------------------------


import org.manish07.dao.GenericsDAO;
import org.manish07.model.Customer;

public interface CustomerService extends GenericsDAO<Customer> {
    
    boolean addCustomer (Customer customer);
    
    Customer getCustomerIdByPhone(String phone);
    
    Customer getCustomerIdByEmail(String email);
}
