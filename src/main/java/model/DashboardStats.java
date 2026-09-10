package model;

import java.util.List;

public class DashboardStats{
    private int totalMedicines;
    private int totalStock;
    private int outOfStockCount;
    private int lowStockCount;
    private int expiringMedicineCount;
    private int totalCustomers;
    private int totalSuppliers;
    private int todayBills;
    private double todaySales;
    private double todayPurchases;
    private List<Medicine> lowStockMedicines;
    private List<Medicine> outOfStockMedicines;
    private List<Medicine> expiringMedicines;
    private List<MedicineSalesStats> topSellingMedicines;
    private List<MedicineSalesStats> leastSellingMedicines;

    public DashboardStats(
            int totalMedicines,
            int totalStock,
            int outOfStockCount,
            int lowStockCount,
            int expiringMedicineCount,
            int totalCustomers,
            int totalSuppliers,
            int todayBills,
            double todaySales,
            double todayPurchases,

            List<Medicine> lowStockMedicines,
            List<Medicine> outOfStockMedicines,
            List<Medicine> expiringMedicines,
            List<MedicineSalesStats> topSellingMedicines,
            List<MedicineSalesStats> leastSellingMedicines
    ){
        this.totalMedicines = totalMedicines;
        this.totalStock = totalStock;
        this.outOfStockCount = outOfStockCount;
        this.lowStockCount = lowStockCount;
        this.expiringMedicineCount = expiringMedicineCount;
        this.totalCustomers = totalCustomers;
        this.totalSuppliers = totalSuppliers;
        this.todayBills = todayBills;
        this.todaySales = todaySales;
        this.todayPurchases = todayPurchases;

        this.lowStockMedicines = lowStockMedicines;
        this.outOfStockMedicines = outOfStockMedicines;
        this.expiringMedicines = expiringMedicines;
        this.topSellingMedicines = topSellingMedicines;
        this.leastSellingMedicines = leastSellingMedicines;
    }

    public int getTotalMedicines() {
        return totalMedicines;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public int getOutOfStockCount() {
        return outOfStockCount;
    }

    public int getLowStockCount() {
        return lowStockCount;
    }

    public int getExpiringMedicineCount() {
        return expiringMedicineCount;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public int getTotalSuppliers() {
        return totalSuppliers;
    }

    public int getTodayBills() {
        return todayBills;
    }

    public double getTodaySales() {
        return todaySales;
    }

    public double getTodayPurchases() {
        return todayPurchases;
    }

    public List<Medicine> getLowStockMedicines(){
        return lowStockMedicines;
    }

    public List<Medicine> getOutOfStockMedicines(){
        return outOfStockMedicines;
    }

    public List<Medicine> getExpiringMedicines(){ return expiringMedicines; }

    public List<MedicineSalesStats> getTopSellingMedicines(){ return topSellingMedicines; }

    public List<MedicineSalesStats> getLeastSellingMedicines(){ return leastSellingMedicines; }
}