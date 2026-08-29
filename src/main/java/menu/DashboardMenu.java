package menu;

import service.DashboardService;
import model.User;
import model.DashboardStats;

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

        System.out.printf(
                "Low Stock Medicines      : %d%n",
                stats.getLowStockCount()
        );

        System.out.printf(
                "Out of Stock Medicines   : %d%n",
                stats.getOutOfStockCount()
        );

        System.out.printf(
                "Expiring Within 30 Days  : %d%n",
                stats.getExpiringMedicineCount()
        );

        System.out.println("""
                ==========================================================
                """);

        System.out.println("Press Enter to return to Main Menu...");
        scanner.nextLine();
    }
}