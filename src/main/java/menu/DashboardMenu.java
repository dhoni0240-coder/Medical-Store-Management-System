package menu;

import model.MedicineSalesStats;
import service.DashboardService;
import model.User;
import model.DashboardStats;
import model.MedicineSalesStats;
import model.Medicine;
import java.util.List;

import java.util.Scanner;

public class DashboardMenu{
    private final Scanner scanner;
    private final DashboardService dashboardService;
    private final User loggedInUser;

    public DashboardMenu(Scanner scanner, User loggedInUser){
        this.scanner = scanner;
        this.loggedInUser = loggedInUser;
        this.dashboardService = new DashboardService();
    }

    public void showDashboard(){
        DashboardStats stats = dashboardService.getDashboardStats();

        System.out.println("""
                ================================================================
                ---------------MEDICAL STORE MANAGEMENT DASHBOARD---------------
                ================================================================""");

        System.out.println("Welcome : " + loggedInUser.getFull_name());
        System.out.println("Role    : " + loggedInUser.getRole());

        System.out.println("""
                ------------------- STORE SUMMARY -----------------------
                """);

        System.out.printf(
                "Total Medicine           : %d%n",
                stats.getTotalMedicines()
        );

        System.out.printf(
                "Total Stock Quantity     : %d%n",
                stats.getTotalStock()
        );

        System.out.printf(
                "Total Customers          : %d%n",
                stats.getTotalCustomers()
        );

        System.out.printf(
                "Total Suppliers          : %d%n",
                stats.getTotalSuppliers()
        );

        System.out.printf(
                "Today's Bills            : %d%n",
                stats.getTodayBills()
        );

        System.out.printf(
                "Today's Sales            : ₹%.2f%n",
                stats.getTodaySales()
        );

        System.out.printf(
                "Today's Purchases        : ₹%.2f%n",
                stats.getTodayPurchases()
        );

        System.out.println("""
                
                ----------------------- ALERTS -------------------------
                """);

        //Low Stock Medicines
        System.out.println("\nLow Stock Medicines : ");
        List<Medicine> lowStockMedicine = stats.getLowStockMedicines();

        if(lowStockMedicine.isEmpty()){
            System.out.println("No low Stock medicines found!");
        }else{
            for(Medicine medicine : lowStockMedicine){
                System.out.printf(
                        " %-30s Stock: %d%n",
                        medicine.getMedicineName(),
                        medicine.getQuantityInStock()
                );
            }
        }

        //Out of Stock Medicines
        System.out.println("\nOut of Stock Medicines:");
        List<Medicine> outOfStockMedicines = stats.getOutOfStockMedicines();

        if(outOfStockMedicines.isEmpty()) {
            System.out.println("No out of stock medicines.");
        } else {
            for (Medicine medicine : outOfStockMedicines) {
                System.out.printf(
                        "  %-30s Stock: %d%n",
                        medicine.getMedicineName(),
                        medicine.getQuantityInStock()
                );
            }
        }

        // Expiring Medicines
        System.out.println("\nExpiring Within 30 Days:");
        List<Medicine> expiringMedicines = stats.getExpiringMedicines();

        if(expiringMedicines.isEmpty()) {
            System.out.println("No medicines expiring within 30 days.");
        } else {
            for (Medicine medicine : expiringMedicines) {
                System.out.printf(
                        "  %-30s Expiry: %s | Stock: %d%n",
                        medicine.getMedicineName(),
                        medicine.getExpiryDate(),
                        medicine.getQuantityInStock()
                );
            }
        }

        System.out.println("\nTop Selling Medicines:");
        List<MedicineSalesStats> topSellingMedicines = stats.getTopSellingMedicines();

        if(topSellingMedicines.isEmpty()){
            System.out.println("No top selling medicines found!");
        }else{
            int rank = 1;
            for(MedicineSalesStats medicine : topSellingMedicines){

                System.out.printf(
                        "%d. %-30s Sold: %d%n",
                        rank++,
                        medicine.getMedicineName(),
                        medicine.getTotalSold()
                );
            }
        }

        System.out.println("\nLeast Selling Medicines:");
        List<MedicineSalesStats> leastSellingMedicines = stats.getLeastSellingMedicines();

        if (leastSellingMedicines.isEmpty()) {
            System.out.println("No sales data available.");
        } else {
            int rank = 1;
            for (MedicineSalesStats medicine : leastSellingMedicines) {

                System.out.printf(
                        "%d. %-30s Sold: %d%n",
                        rank++,
                        medicine.getMedicineName(),
                        medicine.getTotalSold()
                );
            }
        }
        System.out.println("""
                ==========================================================
                """);

        System.out.println("Press Enter to return to Main Menu...");
        scanner.nextLine();
    }
}