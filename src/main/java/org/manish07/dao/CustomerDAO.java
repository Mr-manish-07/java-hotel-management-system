package org.manish07.dao;

import org.manish07.model.Customer;

public interface CustomerDAO extends GenericsDAO<Customer> {
    
    Customer findByPhoneNumber (String phone);

    Customer findByEmail(String phone);

}
