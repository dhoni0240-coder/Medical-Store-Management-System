package menu;

import service.BillingService;
import java.util.Scanner;
import model.User;
import service.InvoicePDFService;

public class BillingMenu {

    private final BillingService billingService;
    private final Scanner scanner;
    private final User loggedInUser;
    private final InvoicePDFService invoicePDFService;

    public BillingMenu(Scanner scanner, User loggedInUser){

        this.scanner = scanner;
        this.loggedInUser = loggedInUser;
        this.billingService = new BillingService(scanner);
        this.invoicePDFService = new InvoicePDFService();
    }

    public void showMenu() {

        while(true) {

            System.out.println("""
                    ======================
                    --- Billing Menu ---
                    ======================
                    1. Generate Bill
                    2. View All Bills
                    3. View Bill details(Bill ID)
                    4. View Bill by Customer Name
                    5. Generate Bill Invoice
                    6. Invoice History
                    0. Back
                    """);

            System.out.print("Enter Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    billingService.generateBill(loggedInUser);
                    break;

                case 2:
                    if(isAdmin() || isPharmacist()){
                        billingService.viewAllBills();
                    }else{
                        accessDenied();
                    }
                    break;

                case 3:
                    if(isAdmin() || isPharmacist()){
                        billingService.viewBillById();
                    }else{
                        accessDenied();
                    }
                    break;

                case 4:
                    if (isAdmin() || isPharmacist()) {
                        billingService.viewBillByCustomerName();
                    } else {
                        accessDenied();
                    }
                    break;

                case 5:
                    generateBillInvoice();
                    break;

                case 6:
                    if(isAdmin() || isPharmacist()){
                        billingService.invoiceHistory();
                    }else{
                        accessDenied();
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
    private void generateBillInvoice(){

        System.out.println("""
            ============================================
            ------------ GENERATE BILL INVOICE ----------
            ============================================
            """);

        System.out.print("Enter Bill ID : ");
        int billId = scanner.nextInt();
        scanner.nextLine();

        invoicePDFService.generateInvoice(billId);
    }
    private boolean isAdmin(){
        return "ADMIN".equals(loggedInUser.getRole());
    }
    private boolean isPharmacist(){
        return "PHARMACIST".equals(loggedInUser.getRole());
    }
    private void accessDenied(){
        System.out.println("Access Denied! You do not have permission to perform this action.");
    }
}