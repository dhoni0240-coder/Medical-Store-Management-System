package service;
import dao.DashboardDAO;
import model.DashboardStats;

public class DashboardService{

    private final DashboardDAO dashboardDAO;

    public DashboardService(){
        this.dashboardDAO = new DashboardDAO();
    }

    public DashboardStats getDashboardStats(){
        int totalMedicines = dashboardDAO.getTotalMedicines();
        int totalStock = dashboardDAO.getTotalStock();
        int outOfStockCount = dashboardDAO.getOutOfStockCount();
        int lowStockCount = dashboardDAO.getLowStockCount();
        int expiringMedicineCount = dashboardDAO.getExpiringMedicineCount();
        int totalCustomers = dashboardDAO.getTotalCustomers();
        int totalSuppliers = dashboardDAO.getTotalSuppliers();
        int todayBills = dashboardDAO.getTodayBills();
        double todaySales = dashboardDAO.getTodaySales();
        double todayPurchases = dashboardDAO.getTodayPurchases();

        return new DashboardStats(
                totalMedicines,
                totalStock,
                outOfStockCount,
                lowStockCount,
                expiringMedicineCount,
                totalCustomers,
                totalSuppliers,
                todayBills,
                todaySales,
                todayPurchases
        );
    }
}