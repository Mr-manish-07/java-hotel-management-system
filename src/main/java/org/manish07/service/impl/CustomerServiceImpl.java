package org.manish07.service.impl;

import org.manish07.Privacy.EmailValidator;
import org.manish07.Privacy.Encryption;
import org.manish07.dao.CustomerDAO;
import org.manish07.model.Customer;
import org.manish07.service.CustomerService;

import java.util.List;

//-----------------------------------------------BUSINESS LOGIC IMPLEMENTATION------------------------------------------


public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;
    
    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
    
    @Override
    public boolean addCustomer(Customer customer) {

        if (customer.getName().length() > 50) {
            System.out.println("Length of name should be smaller than 50 character");
            return false;
        }

        if (!EmailValidator.isValidEmail(customer.getEmail())) {
            System.out.println("Email Id violates the rule , Please use proper Email Id");
            return false;
        }
        
        try {

            long startNumber = Long.parseLong("6000000000");
            long endNumber = Long.parseLong("9999999999");

            long customerPhone = Long.parseLong(customer.getPhone());
            if (!(customerPhone >= startNumber && customerPhone <= endNumber)) {
                System.out.println("Invalid Phone Number ");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        String dbName = customer.getName();
        String dbPhone = Encryption.encrypt(customer.getPhone());
        String dbEmail = Encryption.encrypt(customer.getEmail());
        
        
        return customerDAO.save (new Customer(customer.getCustomerId(),
                dbName,dbPhone,dbEmail,customer.getCreatedAt()));
    }
    
  @Override
    public Customer getCustomerIdByPhone(String phone){
        String encryptedPhoneNumber = Encryption.encrypt(phone);
        return customerDAO.findByPhoneNumber(encryptedPhoneNumber);
    }
    
    @Override
    public Customer getCustomerIdByEmail(String email){
        String encryptedEmail = Encryption.encrypt(email);
        return customerDAO.findByPhoneNumber(encryptedEmail);
    }
    
    @Override
    public boolean save (Customer customer) {
        return customerDAO.save(customer);
    }
    
    @Override
    public boolean update (Customer customer) {
        return customerDAO.update(customer);
    }
    
    @Override
    public boolean delete (Customer customer) {
        return customerDAO.delete (customer);
    }
    
    @Override
    public Customer findById (int id) {
        return customerDAO.findById (id);
    }
    
    @Override
    public List<Customer> findAll () {
        return customerDAO.findAll();
    }
}
