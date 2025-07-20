package org.manish07.service;

//---------------------------------------------------BUSINESS LOGIC METHODS--------------------------------------------


import org.manish07.model.Customer;

import java.util.List;

public interface CustomerService {
    
    boolean addCustomer (Customer customer);
    
    List<Customer> getAllCustomers ();
    
    Customer getCustomerById (int id);
    
    Customer getCustomerIdByPhone(String phone);
    
    Customer getCustomerIdByEmail(String email);
}
