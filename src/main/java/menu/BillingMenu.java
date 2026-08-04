package menu;

import service.BillingService;
import java.util.Scanner;

public class BillingMenu {

    private final BillingService billingService = new BillingService();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {

        while(true) {

            System.out.println("""
                    ======================
                    --- Billing Menu ---
                    ======================
                    1. Generate Bill
                    2. View All Bills
                    3. View Bill by ID
                    4. View Bill by Customer Name
                    0. Back
                    """);

            System.out.print("Enter Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    billingService.generateBill();
                    break;

                case 2:
                    billingService.viewAllBills();
                    break;

                case 3:
                    billingService.viewBillById();
                    break;

                case 4:
                    billingService.viewBillByCustomerName();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}