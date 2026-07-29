package menu;

import dao.SupplierDAO;
import model.Supplier;

import java.util.List;
import java.util.Scanner;

public class SupplierMenu{

    private final SupplierDAO supplierDAO = new SupplierDAO();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu(){
        while(true){
            System.out.println("""
                    =================================
                    ---Medical Store Supplier List---
                    =================================
                    """);
            System.out.println("1. View All Supplier");
            System.out.println("2. Add Supplier");
            System.out.println("3. Update Supplier");
            System.out.println("4. Delete Supplier");
            System.out.println("5. Search Supplier");
            System.out.println("0. Exit");

            System.out.print("Select your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    viewAllSuppliers();
                    break;

                case 2:
                    addSupplier();
                    break;

                case 3:
                    updateSupplier();
                    break;

                case 4:
                    deleteSupplier();
                    break;

                case 5:
                    searchSupplier();
                    break;

                case 0:
                    System.out.println("Thank You!");
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    //View All Supplier
    private void viewAllSuppliers(){
        List<Supplier> suppliers = supplierDAO.getAllSuppliers();

        if(suppliers.isEmpty()){
            System.out.println("No Supplier Found!");

            return;
        }
        for(Supplier supplier : suppliers){
            System.out.println(supplier);
        }
    }

    //Add Supplier
    private void addSupplier(){
        System.out.println("""
                ======================
                ---Add Suppliers---
                ======================
                """);

        System.out.print("Supplier Name : ");
        String supplierName = scanner.nextLine();

        System.out.print("Supplier Phone : ");
        String phone = scanner.nextLine();

        System.out.print("Supplier Email : ");
        String email = scanner.nextLine();

        System.out.print("Supplier Address : ");
        String address = scanner.nextLine();

        System.out.print("Supplier Company Name : ");
        String companyName = scanner.nextLine();

        Supplier supplier = new Supplier(
                0,
                supplierName,
                phone,
                email,
                address,
                companyName
        );
        boolean inserted = supplierDAO.addSupplier(supplier);
        if(inserted){
            System.out.println("Supplier Added Successfully!");
        }else{
            System.out.println("Failed to Add Supplier!");
        }
    }

    //Update Supplier
    private void updateSupplier(){
        System.out.println("""
                ========================
                ---Update Suppliers---
                ========================
                """);

        System.out.print("Supplier ID : ");
        int supplierId = scanner.nextInt();
        scanner.nextLine();

        Supplier supplier = supplierDAO.getSupplierById(supplierId);

        if(supplier == null){
            System.out.println("Supplier Not found!");
            return;
        }
        System.out.println("\nCurrent Supplier detail : ");
        System.out.println(supplier);

        System.out.print("\nEnter new Supplier Name : ");
        String newSupplierName = scanner.nextLine();

        System.out.print("\nEnter new Supplier Phone :");
        String newPhone = scanner.nextLine();

        System.out.print("\nEnter new Supplier Email : ");
        String newEmail = scanner.nextLine();

        System.out.print("\nEnter new Supplier Address : ");
        String newAddress = scanner.nextLine();

        System.out.print("\nEnter new Supplier Company Name : ");
        String newCompanyName = scanner.nextLine();

        supplier.setSupplierName(newSupplierName);
        supplier.setPhone(newPhone);
        supplier.setEmail(newEmail);
        supplier.setAddress(newAddress);
        supplier.setCompanyName(newCompanyName);

        boolean updated = supplierDAO.updateSupplier(supplier);
        if(updated){
            System.out.println("\nSupplier Updated Successfully!");
        }else{
            System.out.println("\nFailed to add Supplier!");
        }
    }

    //For Delete Supplier
    private void deleteSupplier() {
        System.out.println("""
                =======================
                ---Delete Suppliers---
                =======================
                """);
        System.out.print("Enter Supplier ID : ");
        int supplierId = scanner.nextInt();
        scanner.nextLine();

        Supplier supplier = supplierDAO.getSupplierById(supplierId);

        if (supplier == null) {
            System.out.println("Supplier Not Found");
            return;
        }
        //Show supplier details
        System.out.print("\nCurrent Supplier Details : ");
        System.out.println(supplier);

        //Confirmation before deleting
        System.out.print("\nAre you sure want to delete this Supplier ?(Y/N) : ");
        String choice = scanner.nextLine();

        if (!choice.equalsIgnoreCase("Y")) {
            System.out.println("Delete Operation Cancelled.");
            return;
        }

        //Delete Supplier
        boolean deleted = supplierDAO.deleteSupplier(supplierId);

        if (deleted) {
            System.out.println("\nSupplier deleted Successfully!");
        } else {
            System.out.println("\nFailed to delete Supplier!");
        }
    }

    //For searching Suppliers
    private void searchSupplier(){
        System.out.println("""
                =======================
                ---Search Suppliers ---
                =======================
                """);
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Phone");
        System.out.println("4. Search by Email");
        System.out.println("5. Search by CompanyName");
        System.out.println("0. Back");

        System.out.print("Enter Choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                searchSupplierById();
                break;

            case 2:
                searchSupplierByName();
                break;

            case 3:
                searchSupplierByPhone();
                break;

            case 4:
                searchSupplierByEmail();
                break;

            case 5:
                searchSupplierByCompanyName();
                break;

            case 0:
                return;
            default:
                System.out.println("Invalid Choice");
        }
    }

    //Search by Supplier id
    private void searchSupplierById() {
        System.out.println("""
                ===========================
                ---Search Supplier by ID---
                ===========================
                """);
        System.out.print("Enter Supplier ID : ");
        int supplierId = scanner.nextInt();
        scanner.nextLine();

        Supplier supplier = supplierDAO.getSupplierById(supplierId);

        if (supplier == null) {
            System.out.println("\nSupplier not Found!");
        } else {
            System.out.println("\nSupplier Found Successfully!");
            System.out.println("-------------------------------------------------------");
            System.out.println(supplier);
            System.out.println("-------------------------------------------------------");
        }
    }

    //Search Supplier by Name
    private void searchSupplierByName() {
        System.out.println("""
                =============================
                ---Search Supplier by Name---
                =============================
                """);

        System.out.print("Enter Supplier Name : ");
        String supplierName = scanner.nextLine();

        List<Supplier> supplierList = supplierDAO.searchSupplierByName(supplierName);

        if (supplierList.isEmpty()) {
            System.out.println("\nSupplier Not Found!");
            return;
        }

        System.out.println("\nSupplier(s) Found : ");
        System.out.println("=============================================================");

        for (Supplier supplier : supplierList) {
            System.out.println(supplier);
            System.out.println("---------------------------------------------------------");
        }
    }

    //Search by Phone Number
    private void searchSupplierByPhone() {
        System.out.println("""
                =========================
                ---Search by Phone Number---
                =========================""");
        System.out.print("Enter Phone Number : ");
        String phone = scanner.nextLine();

        List<Supplier> supplierList = supplierDAO.searchSupplierByPhone(phone);

        if (supplierList.isEmpty()) {
            System.out.println("\nSupplier Not Found!");
            return;
        }

        System.out.println("\nSupplier Found : ");
        System.out.println("============================================================");

        for (Supplier supplier : supplierList) {
            System.out.println(supplier);
            System.out.println("--------------------------------------------------------");
        }
    }

    //Search by Phone Number
    private void searchSupplierByEmail() {
        System.out.println("""
                =========================
                ---Search by Email ID---
                =========================""");
        System.out.print("Enter Email ID : ");
        String email = scanner.nextLine();

        List<Supplier> supplierList = supplierDAO.searchSupplierByEmail(email);

        if (supplierList.isEmpty()) {
            System.out.println("\nSupplier Not Found!");
            return;
        }

        System.out.println("\nSupplier Found : ");
        System.out.println("============================================================");

        for (Supplier supplier : supplierList) {
            System.out.println(supplier);
            System.out.println("--------------------------------------------------------");
        }
    }

    //Search by Phone Number
    private void searchSupplierByCompanyName() {
        System.out.println("""
                =========================
                ---Search by Phone Number---
                =========================""");
        System.out.print("Enter Company Name : ");
        String companyName = scanner.nextLine();

        List<Supplier> supplierList = supplierDAO.searchSupplierByCompanyName(companyName);

        if (supplierList.isEmpty()) {
            System.out.println("\nSupplier Not Found!");
            return;
        }

        System.out.println("\nSupplier Found : ");
        System.out.println("============================================================");

        for (Supplier supplier : supplierList) {
            System.out.println(supplier);
            System.out.println("--------------------------------------------------------");
        }
    }
}
