package org.manish07.dao;

import org.manish07.model.Customer;

import java.util.List;

public interface CustomerDAO {


//---------------------------------------------ABSTRACT METHOD OF CUSTOMER CLASS----------------------------------------

    boolean addCustomer(Customer customer);

    Customer getCustomerById(int id) ;

    List<Customer> getAllCustomers() ;
    
    public int getCustomerIdByPhone(String phone);

}
