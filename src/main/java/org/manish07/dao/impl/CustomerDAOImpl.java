package org.manish07.dao.impl;

import org.manish07.GenericsDOA.GenericDAO;
import org.manish07.dao.CustomerDAO;
import org.manish07.model.Customer;
import org.manish07.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


//------------------------------- C O M M U N I C A T I O N    W I T H   D A T A B A S E -------------------------------


public class CustomerDAOImpl implements CustomerDAO {

    private final Connection connection = DBUtil.getConnection();
    private static final Logger logger = Logger.getLogger(CustomerDAOImpl.class.getName());
    GenericDAO dao = new GenericDAO(connection);



//-----------------------------------------------------ADD customers -------------------------------------------------

    @Override
    public boolean addCustomer(Customer customer) {

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO customers(name,phone,email,created_at) VALUES (?,?,?,?)")) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            ps.setTimestamp(4, Timestamp.valueOf(customer.getCreatedAt()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding customer to the database", e);
            return false;
        }
    }


//----------------------------------------------------GET CUSTOMER BY ID----------------------------------------------

    @Override
    public Customer getCustomerById(int id) {

        return dao.query(
                "SELECT * FROM customers WHERE id = ?",
                ps -> ps.setInt(1, id),
                rs -> new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getTimestamp("created_at").toLocalDateTime())

        );
    }


//----------------------------------------------------GET LIST OF customers -------------------------------------------

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> customerList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM customers")) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                customerList.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getTimestamp("created_at").toLocalDateTime()));
            }
            return customerList;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error Getting All customers from the database", e);
            return  null;
        }
    }


//----------------------------------------------------GET Customer BY PHONE--------------------------------------------

    @Override
    public int getCustomerIdByPhone(String phone) {

        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM customers WHERE phone = ?")) {

            ps.setString(1, phone);

            ResultSet rs = ps.executeQuery();

            return rs.next() ? rs.getInt("id") : -1;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error Getting while fetching customer by phone from the database", e);
        }
        return -1;
    }

    public static void setResultSet_Customers(){}

}