package app;

import menu.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ReportMenu reportMenu = new ReportMenu();
        MedicineMenu medicineMenu = new MedicineMenu();
        CustomerMenu customerMenu = new CustomerMenu();
        SupplierMenu supplierMenu = new SupplierMenu();
        BillingMenu billingMenu = new BillingMenu();

        while (true) {
            System.out.println("""
                    =====================================
                    ---MEDICAL STORE MANAGEMENT SYSTEM---
                    =====================================
                    1. Medicine Management
                    2. Supplier Management
                    3. Customer Management
                    4. Billing Management
                    5. Reports
                    0. Back
                    """);

            System.out.print("Enter your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    medicineMenu.showMenu();
                    break;

                case 2:
                    supplierMenu.showMenu();
                    break;

                case 3:
                    customerMenu.showMenu();
                    break;

                case 4:
                    billingMenu.showMenu();
                    break;

                case 5:
                    reportMenu.showMenu();
                    break;

                case 0:
                    System.out.println("Thank you");
                    System.exit(0);
                    break;

                default:
                    System.out.print("Invalid Choice!");
            }
        }
    }
}