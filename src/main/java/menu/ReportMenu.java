package menu;

import service.ReportService;
import java.util.Scanner;

public class ReportMenu{

    private final ReportService reportService = new ReportService();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu(){
        while(true){
            System.out.println("""
                    =======================
                    ------REPORT MENU------
                    =======================
                    1. Low Stock Medicines
                    2. Out of Stock Medicines
                    3. Expiring Medicines
                    4. Daily Selling Report
                    5. Monthly Selling Report
                    6. Top Selling Medicines
                    7. Profit Report
                    0. Back
                    """);

            System.out.print("Enter your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    reportService.lowStockMedicines();
                    break;

                case 2:
                    reportService.outOfStockMedicines();
                    break;

                case 3:
                    reportService.expiringMedicines();
                    break;

                case 4:
                    reportService.dailySalesReport();
                    break;

                case 5:
                    reportService.monthlySalesReport();
                    break;

                case 6:
                    reportService.topSellingMedicines();
                    break;

                case 7:
                    reportService.profitReport();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}