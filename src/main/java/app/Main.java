package app;

import menu.*;

import java.util.Scanner;
import model.User;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Login
        LoginMenu loginMenu = new LoginMenu(scanner);
        User loggedInUser = null;

        while(loggedInUser == null){
            loggedInUser = loginMenu.login();
        }

        ReportMenu reportMenu = new ReportMenu();
        MedicineMenu medicineMenu = new MedicineMenu();
        CustomerMenu customerMenu = new CustomerMenu();
        SupplierMenu supplierMenu = new SupplierMenu();
        BillingMenu billingMenu = new BillingMenu(scanner, loggedInUser);
        PurchaseMenu purchaseMenu = new PurchaseMenu(scanner, loggedInUser);

        boolean logout = false;

        while (!logout) {
            System.out.println("""
                    =====================================
                    ---MEDICAL STORE MANAGEMENT SYSTEM---
                    =====================================
                    Logged in as : %s (%s)
                    
                    1. Medicine Management
                    2. Supplier Management
                    3. Customer Management
                    4. Billing Management
                    5. Reports
                    6. Purchase Management
                    7. Logout
                    0. Exit
                    """.formatted(
                            loggedInUser.getFull_name(),
                            loggedInUser.getRole()
                    ));

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

                case 6:
                    purchaseMenu.showMenu();
                    break;

                case 7:
                    System.out.println("\nLogging out...");
                    loggedInUser = null;

                    while(loggedInUser == null){
                        loggedInUser = loginMenu.login();
                    }
                    break;

                case 0:
                    System.out.println("Thank you for using our application!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.print("Invalid Choice!");
            }
        }
    }
}