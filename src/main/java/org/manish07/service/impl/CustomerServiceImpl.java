package org.manish07.service.impl;

import org.manish07.Privacy.EmailValidator;
import org.manish07.Privacy.Encryption;
import org.manish07.dao.impl.CustomerDAOImpl;
import org.manish07.model.Customer;
import org.manish07.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

//-----------------------------------------------BUSINESS LOGIC IMPLEMENTATION------------------------------------------


public class CustomerServiceImpl implements CustomerService {

    CustomerDAOImpl customerDAO = new CustomerDAOImpl();

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


        return customerDAO.addCustomer(new Customer(customer.getCustomerId(),
                dbName,dbPhone,dbEmail,customer.getCreatedAt()));
    }

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> encryptedCustomers = customerDAO.getAllCustomers();
        List<Customer> listOfCustomers = new ArrayList<>();

        for(Customer customer : encryptedCustomers){

            String name = customer.getName();
            String phone = Encryption.decrypt(customer.getPhone());
            String email = Encryption.decrypt(customer.getEmail());

            listOfCustomers.add(new Customer(customer.getCustomerId() , name,phone,email,customer.getCreatedAt()));
        }
        return listOfCustomers;
    }
    
    @Override
    public Customer getCustomerById (int id) {

        Customer incryptedCustomer = customerDAO.getCustomerById(id);

        String name = incryptedCustomer.getName();
        String email = Encryption.decrypt(incryptedCustomer.getEmail());
        String phone = Encryption.decrypt(incryptedCustomer.getPhone());

        return  new Customer(incryptedCustomer.getCustomerId(),name,phone,email,incryptedCustomer.getCreatedAt());
    }
    
    @Override
    public int getCustomerIdByPhone(String phone){
        String encrypt = Encryption.encrypt(phone);
        return customerDAO.getCustomerIdByPhone(encrypt);
    }
}
