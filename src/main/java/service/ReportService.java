package service;

import dao.ReportDAO;
import model.Medicine;
import java.util.List;

public class ReportService{

    private final ReportDAO reportDAO = new ReportDAO();

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
}