package org.manish07.service;

//---------------------------------------------------BUSINESS LOGIC METHODS--------------------------------------------


import org.manish07.model.Customer;

import java.util.List;

public interface CustomerService {
    
    boolean addCustomer (Customer customer);
    
    Customer getCustomerById(int id) ;
    
    List<Customer> getAllCustomers () ;
    
    int getCustomerIdByPhone(String phone);
}
