package menu;

import dao.CustomerDAO;
import model.Customer;
import model.User;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu{

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final Scanner scanner;
    private final User loggedInUser;

    public CustomerMenu(Scanner scanner, User loggedInUser){
        this.scanner = scanner;
        this.loggedInUser = loggedInUser;
    }

    public void showMenu(){
        while(true){
            System.out.println("""
                    =================================
                    ---Medical Store Customer List---
                    =================================
                    """);
            System.out.println("1. View All Customers");
            System.out.println("2. Add Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Search Customer");
            System.out.println("0. Exit");

            System.out.print("Select your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    viewAllCustomers();
                    break;

                case 2:
                    if(isAdmin() || isPharmacist()){
                        addCustomer();
                    }else{
                        accessDenied();
                    }
                    break;

                case 3:
                    if(isAdmin() || isPharmacist()){
                        updateCustomer();
                    }else{
                        accessDenied();
                    }
                    break;

                case 4:
                    if(isAdmin()){
                        deleteCustomer();
                    }else{
                        accessDenied();
                    }
                    break;

                case 5:
                    searchCustomer();
                    break;

                case 0:
                    System.out.println("Thank You!");
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
    private boolean isAdmin(){
        return "ADMIN".equals(loggedInUser.getRole());
    }
    private boolean isPharmacist(){
        return "PHARMACIST".equals(loggedInUser.getRole());
    }
    private void accessDenied(){
        System.out.println("\nAccess denied! You do not have permission to perform this action.");
    }

    //View All Customer
    private void viewAllCustomers(){
        List<Customer> customers = customerDAO.getAllCustomers();

        if(customers.isEmpty()){
            System.out.println("No Customer Found!");

            return;
        }
        for(Customer customer : customers){
            System.out.println(customer);
        }
    }

    //Add Customer
    private void addCustomer(){
        System.out.println("""
                ======================
                ---Add Customers---
                ======================
                """);

        System.out.print("Customer Name : ");
        String customerName = scanner.nextLine();

        System.out.print("Customer Phone : ");
        String phone = scanner.nextLine();

        System.out.print("Customer Email : ");
        String email = scanner.nextLine();

        System.out.print("Customer Address : ");
        String address = scanner.nextLine();

        Customer customer = new Customer(
                0,
                customerName,
                phone,
                email,
                address
        );
        boolean inserted = customerDAO.addCustomer(customer);
        if(inserted){
            System.out.println("Customer Added Successfully!");
        }else{
            System.out.println("Failed to Add Customer!");
        }
    }

    //Update Customer
    private void updateCustomer(){
        System.out.println("""
                ========================
                ---Update Customers---
                ========================
                """);

        System.out.print("Customer ID : ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerDAO.getCustomerById(customerId);

        if(customer == null){
            System.out.println("Customer Not found!");
            return;
        }
        System.out.println("\nCurrent Customer detail : ");
        System.out.println(customer);

        System.out.print("\nEnter new Customer Name : ");
        String newCustomerName = scanner.nextLine();

        System.out.print("\nEnter new Customer Phone :");
        String newPhone = scanner.nextLine();

        System.out.print("\nEnter new Customer Email : ");
        String newEmail = scanner.nextLine();

        System.out.print("\nEnter new Customer Address : ");
        String newAddress = scanner.nextLine();

        customer.setCustomerName(newCustomerName);
        customer.setPhone(newPhone);
        customer.setEmail(newEmail);
        customer.setAddress(newAddress);

        boolean updated = customerDAO.updateCustomer(customer);
        if(updated){
            System.out.println("\nCustomer Updated Successfully!");
        }else{
            System.out.println("\nFailed to add Customer!");
        }
    }

    //For Delete Customer
    private void deleteCustomer() {
        System.out.println("""
                =======================
                ---Delete Customers---
                =======================
                """);
        System.out.print("Enter Customer ID : ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerDAO.getCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer Not Found");
            return;
        }
        //Show supplier details
        System.out.print("\nCurrent Customer Details : ");
        System.out.println(customer);

        //Confirmation before deleting
        System.out.print("\nAre you sure want to delete this Customer ?(Y/N) : ");
        String choice = scanner.nextLine();

        if (!choice.equalsIgnoreCase("Y")) {
            System.out.println("Delete Operation Cancelled.");
            return;
        }

        //Delete Customer
        boolean deleted = customerDAO.deleteCustomer(customerId);

        if (deleted) {
            System.out.println("\nCustomer deleted Successfully!");
        } else {
            System.out.println("\nFailed to delete Customer!");
        }
    }

    //For searching Customers
    private void searchCustomer(){
        System.out.println("""
                =======================
                ---Search Customers ---
                =======================
                """);
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Phone");
        System.out.println("4. Search by Email");
        System.out.println("0. Back");

        System.out.print("Enter Choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                searchCustomerById();
                break;

            case 2:
                searchCustomerByName();
                break;

            case 3:
                searchCustomerByPhone();
                break;

            case 4:
                searchCustomerByEmail();
                break;

            case 0:
                return;
            default:
                System.out.println("Invalid Choice");
        }
    }

    //Search by Customer id
    private void searchCustomerById() {
        System.out.println("""
                ===========================
                ---Search Customer by ID---
                ===========================
                """);
        System.out.print("Enter Customer ID : ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerDAO.getCustomerById(customerId);

        if (customer == null) {
            System.out.println("\nCustomer not Found!");
        } else {
            System.out.println("\nCustomer Found Successfully!");
            System.out.println("-------------------------------------------------------");
            System.out.println(customer);
            System.out.println("-------------------------------------------------------");
        }
    }

    //Search Customer by Name
    private void searchCustomerByName() {
        System.out.println("""
                =============================
                ---Search Customer by Name---
                =============================
                """);

        System.out.print("Enter Customer Name : ");
        String customerName = scanner.nextLine();

        List<Customer> customerList = customerDAO.searchCustomerByName(customerName);

        if (customerList.isEmpty()) {
            System.out.println("\nCustomer Not Found!");
            return;
        }

        System.out.println("\nCustomer(s) Found : ");
        System.out.println("=============================================================");

        for (Customer customer : customerList) {
            System.out.println(customer);
            System.out.println("---------------------------------------------------------");
        }
    }

    //Search by Phone Number
    private void searchCustomerByPhone() {
        System.out.println("""
                =========================
                ---Search by Phone Number---
                =========================""");
        System.out.print("Enter Phone Number : ");
        String phone = scanner.nextLine();

        List<Customer> customerList = customerDAO.searchCustomerByPhone(phone);

        if (customerList.isEmpty()) {
            System.out.println("\nCustomer Not Found!");
            return;
        }

        System.out.println("\nCustomer Found : ");
        System.out.println("============================================================");

        for (Customer customer : customerList) {
            System.out.println(customer);
            System.out.println("--------------------------------------------------------");
        }
    }

    //Search by Phone Number
    private void searchCustomerByEmail() {
        System.out.println("""
                =========================
                ---Search by Email ID---
                =========================""");
        System.out.print("Enter Email ID : ");
        String email = scanner.nextLine();

        List<Customer> customerList = customerDAO.searchCustomerByEmail(email);

        if (customerList.isEmpty()) {
            System.out.println("\nCustomer Not Found!");
            return;
        }

        System.out.println("\nCustomer Found : ");
        System.out.println("============================================================");

        for (Customer customer : customerList) {
            System.out.println(customer);
            System.out.println("--------------------------------------------------------");
        }
    }
}