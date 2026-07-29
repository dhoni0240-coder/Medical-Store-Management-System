package dao;

import config.DatabaseConnection;
import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO{
    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM customers";

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
               int customerId = resultSet.getInt("customer_id");
               String customerName = resultSet.getString("customer_name");
               String phone = resultSet.getString("phone");
               String email = resultSet.getString("email");
               String address = resultSet.getString("address");

               Customer customer = new Customer(
                       customerId,
                       customerName,
                       phone,
                       email,
                       address
               );
               customers.add(customer);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return customers;
    }

    //=================
    // Add Customers
    //=================

    public boolean addCustomer(Customer customer){
        String sql = """
                INSERT INTO customers
                (
                customer_name,
                phone,
                email,
                address
                )
                VALUES
                (?,?,?,?)
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getAddress());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //get Customer by ID
    public Customer getCustomerById(int customerId){

        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String customerName = resultSet.getString("customer_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                return new Customer(
                        id,
                        customerName,
                        phone,
                        email,
                        address
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Search customer by Name
    public List<Customer> searchCustomerByName(String customerName){
        List<Customer> customerList = new ArrayList<>();

        String sql = """
                SELECT * FROM customers
                WHERE customer_name LIKE ?
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            preparedStatement.setString(1, "%" +customerName+ "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                String customerNameResult = resultSet.getString("customer_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                Customer customer = new Customer(
                        customerId,
                        customerNameResult,
                        phone,
                        email,
                        address
                );
                customerList.add(customer);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return customerList;
    }

    //Search Customer by Phone Number
    public List<Customer> searchCustomerByPhone(String phone){
        List<Customer> customerList = new ArrayList<>();

        String sql = """
                SELECT * FROM customers
                WHERE phone LIKE ?
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            preparedStatement.setString(1, "%" +phone+ "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                String customerName = resultSet.getString("customer_name");
                String phoneResult = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                Customer customer = new Customer(
                        customerId,
                        customerName,
                        phoneResult,
                        email,
                        address
                );
                customerList.add(customer);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return customerList;
    }

    //Search Customer by Email
    public List<Customer> searchCustomerByEmail(String email){
        List<Customer> customerList = new ArrayList<>();

        String sql = """
                SELECT * FROM customers
                WHERE email LIKE ?
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            preparedStatement.setString(1, "%" +email+ "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                String customerName = resultSet.getString("customer_name");
                String phone = resultSet.getString("phone");
                String emailResult = resultSet.getString("email");
                String address = resultSet.getString("address");

                Customer customer = new Customer(
                        customerId,
                        customerName,
                        phone,
                        emailResult,
                        address
                );
                customerList.add(customer);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return customerList;
    }

    //Update Customer
    public boolean updateCustomer(Customer customer){
        String sql = """
                UPDATE customers
                SET
                customer_name = ?,
                phone = ?,
                email = ?,
                address = ?
                WHERE customer_id = ?""";

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setInt(5, customer.getCustomerId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //Delete Customer
    public boolean deleteCustomer(int customerId){
        String sql = """
                DELETE FROM customers
                WHERE customer_id = ?
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setInt(1, customerId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}