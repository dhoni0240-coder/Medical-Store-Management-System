package model;

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
            double todayPurchases
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
}