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
                    System.out.println("Update Supplier - Coming Soon");
                    break;

                case 4:
                    System.out.println("Delete Supplier - Coming Soon");
                    break;

                case 5:
                    System.out.println("Search Supplier - Coming Soon");
                    break;

                case 0:
                    System.out.println("Thank You!");
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    //View All Medicine
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
}
