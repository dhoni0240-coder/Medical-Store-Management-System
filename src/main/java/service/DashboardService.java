package service;
import dao.DashboardDAO;
import model.DashboardStats;
import model.MedicineSalesStats;
import model.Medicine;
import java.util.List;

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
        List<Medicine> lowStockMedicines = dashboardDAO.getLowStockMedicines();
        List<Medicine> outOfStockMedicines = dashboardDAO.getOutOfStockMedicines();
        List<Medicine> expiringMedicines = dashboardDAO.getExpiringMedicines();
        List<MedicineSalesStats> topSellingMedicines = dashboardDAO.getTopSellingMedicines();
        List<MedicineSalesStats> leastSellingMedicines = dashboardDAO.getLeastSellingMedicines();

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
                todayPurchases,
                lowStockMedicines,
                outOfStockMedicines,
                expiringMedicines,
                topSellingMedicines,
                leastSellingMedicines
        );
    }
}