package service;

import java.util.Scanner;
import model.Bill;
import dao.ReportDAO;
import model.Medicine;
import java.util.List;

public class ReportService{

    private final ReportDAO reportDAO = new ReportDAO();

    Scanner scanner = new Scanner(System.in);

    public void lowStockMedicines(){
        List<Medicine> medicineList = reportDAO.lowStockMedicines();

        if(medicineList.isEmpty()){
            System.out.println("\nNo low stock medicines found");
            return;
        }
        System.out.println("""
                    =====================================
                    ---------LOW MEDICINE STOCK---------
                    =====================================
                    ID  Medicine Name              Stock
                    -------------------------------------
                    """);
        for(Medicine medicine : medicineList){
            System.out.printf(""+
                    "%-4d %-25s %-5d%n",
            medicine.getMedicineId(),
            medicine.getMedicineName(),
            medicine.getQuantityInStock()
            );
        }
        System.out.println("======================================");
    }

    //Out of Stock Medicine
    public void outOfStockMedicines(){

        List<Medicine> medicineList = reportDAO.outOfStockMedicines();

        if(medicineList.isEmpty()){
            System.out.print("\nNo out of stock medicines found!");
            return;
        }

        System.out.println("""
                =======================================
                ---------OUT OF STOCK------------------
                =======================================
                ID  Medicine Name             Rack No.
                ---------------------------------------
                """);
        for(Medicine medicine : medicineList){
            System.out.printf("" +
                    "%-4d %-25s %-10s%n",
                    medicine.getMedicineId(),
                    medicine.getMedicineName(),
                    medicine.getRackNo()
            );
        }
        System.out.println("=====================================");
    }

    //Expiring Medicines
    public void expiringMedicines(){

        List<Medicine> medicineList = reportDAO.expiringMedicines();

        if(medicineList.isEmpty()){
            System.out.println("\nNo Expiring Medicines found!");
            return;
        }
        System.out.println("""
                =======================================================
                ---------------Expiring Medicines----------------------
                =======================================================
                ID   Medicine Name            Expiry Date      Rack No.
                =======================================================
                """);

        for(Medicine medicine : medicineList){
            System.out.printf("" +
                    "%-4d %-25s %-15s %-10s%n",
                    medicine.getMedicineId(),
                    medicine.getMedicineName(),
                    medicine.getExpiryDate(),
                    medicine.getRackNo()
            );
        }
        System.out.println("======================================================");
    }

    //Daily Sales Report
    public void dailySalesReport(){

        List<Bill> billList = reportDAO.dailyStockReport();

        if(billList.isEmpty()){
            System.out.println("\nNo Sales found for today!");
            return;
        }

        double totalSales = 0;

        System.out.println("""
                =================================================================
                ----------------DAILY SALES REPORTS------------------------------
                =================================================================
                Bill ID   Customer ID   Date                  Final Amount
                -----------------------------------------------------------------
                """);
        for(Bill bill : billList){
            System.out.printf("" +
                    "%-9d %-13d %-21s ₹%.2f%n",
                    bill.getBillId(),
                    bill.getCustomerId(),
                    bill.getBillDate(),
                    bill.getFinalAmount()
            );
            totalSales += bill.getFinalAmount();
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Total Bills today : " +billList.size());
        System.out.printf("Today's total Sales : ₹%.2f%n",totalSales);
        System.out.println("==================================================================");
    }

    //Monthly Sales Report
    public void monthlySalesReport(){

        System.out.print("Enter Month(1-12) : ");
        int month = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Year : ");
        int year = scanner.nextInt();
        scanner.nextLine();

        List<Bill> billList = reportDAO.monthlySalesReport(month, year);

        if(billList.isEmpty()){
            System.out.println("\nNo Sales found for this Month!");
            return;
        }

        double totalSales = 0;

        System.out.println("""
                ============================================================
                -----------------MONTHLY SALES REPORTS----------------------
                ============================================================
                Bill ID   Customer ID   Date                 Final Amount
                ------------------------------------------------------------
                """);
        for(Bill bill : billList){
            System.out.printf(
                    "%-9d %-13d %-21s ₹%.2f%n",
                    bill.getBillId(),
                    bill.getCustomerId(),
                    bill.getBillDate(),
                    bill.getFinalAmount()
            );
            totalSales += bill.getFinalAmount();
        }
        System.out.println("--------------------------------------------------------------");
        System.out.println("Total Bills : " + billList.size());
        System.out.printf("Monthly Sales : ₹%.2f%n", totalSales);
        System.out.println("==============================================================");
    }

    //Top Selling Medicines
    public void topSellingMedicines(){

        List<Object[]> medicineList = reportDAO.topSellingMedicines();

        if(medicineList.isEmpty()){
            System.out.println("\nNo top medicines found!");
            return;
        }
        System.out.println("""
                ==================================================================
                --------------------TOP SELLING MEDICINES--------------------
                ==================================================================
                Medicine ID   Medicine Name            Quantity Sold
                ------------------------------------------------------------------
                """);

        for(Object[] row : medicineList){

            System.out.printf(
                    "%-13d %-25s %-10d%n",
                    (Integer) row[0],
                    (String) row[1],
                    (Integer) row[2]
            );
        }
        System.out.println("=====================================================================");
    }

    //Profit Report
    public void profitReport(){

        double[] report = reportDAO.profitReport();

        double revenue = report[0];
        double cost = report[1];
        double profit = report[2];

        System.out.println("""
                ============================================================
                --------------------PROFIT REPORT--------------------
                ============================================================
                """);

        System.out.printf("Total Revenue : ₹%.2f%n", revenue);
        System.out.printf("Total Cost    : ₹%.2f%n", cost);
        System.out.println("----------------------------------------------");
        System.out.printf("Net Profit    : ₹%.2f%n", profit);

        System.out.println("==========================================================");
    }
}