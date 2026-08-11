package menu;

import model.Purchase;
import model.PurchaseItem;
import service.PurchaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PurchaseMenu{

    private final Scanner scanner;
    private final PurchaseService purchaseService;

    public PurchaseMenu(Scanner scanner){

        this.scanner = scanner;
        this.purchaseService = new PurchaseService();

    }

    public void showMenu(){

        while(true) {
            System.out.println("""
                    =============================================
                    ------------ PURCHASE MANAGEMENT ------------
                    =============================================
                    1. Create a new purchase
                    2. View Purchase History
                    3. View Purchase Details
                    0. Back
                    """);

            System.out.print("Enter your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createPurchase();
                    break;

                case 2:
                    purchaseHistory();
                    break;

                case 3:
                    purchaseDetails();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
    private void createPurchase(){
        System.out.println("""
                ===================================================
                --------------- CREATE NEW PURCHASE ---------------
                ===================================================
                """);

        System.out.print("Enter Supplier ID : ");
        int supplierId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter User ID : ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        List<PurchaseItem> purchaseItems = new ArrayList<>();

        while(true){
            System.out.print("\nEnter Medicine ID : ");
            int medicineId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Quantity : ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Purchase price : ");
            double purchasePrice = scanner.nextDouble();
            scanner.nextLine();

            PurchaseItem purchaseItem = new PurchaseItem(
                    0,
                    0,
                    medicineId,
                    quantity,
                    purchasePrice,
                    0
            );
            purchaseItems.add(purchaseItem);
            System.out.print("Add another medicine ? : ");

            String yes = scanner.nextLine();

            if(!yes.equalsIgnoreCase("y")){
                break;
            }
        }
        int purchaseId = purchaseService.createPurchase(supplierId, userId, purchaseItems);
        if(purchaseId == -1){
            System.out.println("\nPurchase creation failed!");
        }
    }

    public void purchaseHistory(){

        List<Purchase> purchaseList = purchaseService.getAllPurchases();
        if(purchaseList.isEmpty()){
            System.out.println("\nNo purchase found!");
            return;
        }
        System.out.println("""
                ============================================================
                ---------------------- PURCHASE HISTORY --------------------
                ============================================================
                ID        Supplier ID    User ID    Date          Amount
                ------------------------------------------------------------
                """);
        for(Purchase purchase : purchaseList){
            System.out.printf(
                    "%-9d %-14d %-10d %-13s ₹%.2f%n",
                    purchase.getPurchaseId(),
                    purchase.getSupplierId(),
                    purchase.getUserId(),
                    purchase.getPurchaseDate(),
                    purchase.getTotalAmount()
            );
        }
        System.out.println("=========================================================");
    }

    public void purchaseDetails(){
        System.out.println("""
                ============================================================
                ---------------------- PURCHASE DETAILS --------------------
                ============================================================
                """);

        System.out.print("Enter Purchase ID : ");
        int purchaseId = scanner.nextInt();
        scanner.nextLine();

        List<PurchaseItem> purchaseList = purchaseService.getPurchaseItems(purchaseId);

        if(purchaseList.isEmpty()){
            System.out.println("\nNo items found for purchase ID : " +purchaseId);
            return;
        }
        double total = 0;

        System.out.println("""
                ======================================================================
                --------------------- PURCHASE DETAILS -------------------------------
                ======================================================================
                Medicine ID   Medicine Name       Quantity   Purchase Price   Subtotal
                ----------------------------------------------------------------------
                """);
        for(PurchaseItem item : purchaseList){
            System.out.printf(
                    "%-13d %-20s %-10d ₹%-15.2f ₹%.2f%n",
                    item.getMedicineId(),
                    item.getMedicineName(),
                    item.getQuantity(),
                    item.getPurchasePrice(),
                    item.getSubTotal()
            );
            total += item.getSubTotal();
        }
        System.out.println("--------------------------------------------------------------");
        System.out.printf("Total Amount : ₹%.2f%n",total);
        System.out.println("===============================================================");
    }
}